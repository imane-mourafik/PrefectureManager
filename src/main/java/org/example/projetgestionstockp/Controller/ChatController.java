package org.example.projetgestionstockp.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.projetgestionstockp.Model.Message;
import org.example.projetgestionstockp.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MessageRepository messageRepo;

    @MessageMapping("/chat")
    public void handleMessage(@Payload Message message) {
        message.setTimestamp(LocalDateTime.now());
        Message saved = messageRepo.save(message);
        messagingTemplate.convertAndSend("/topic/messages", saved);
    }



    @GetMapping("/user")
    @ResponseBody
    public String currentUser(HttpSession session) {
        String username = (String) session.getAttribute("username");
        return (username != null) ? username : "Anonymous";
    }


    @GetMapping("/discussion")
    public String discussionPage() {
        return "discussion"; // Ce fichier doit être dans /templates/discussion.html (si tu utilises Thymeleaf)
    }


    @PostMapping("/messages/delete/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long id, Principal user) {
        Message msg = messageRepo.findById(id).orElse(null);
        if (msg != null && msg.getSender().equals(user.getName())) {
            msg.setDeleted(true);
            messageRepo.save(msg);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("/messages")
    @ResponseBody
    public List<Message> getMessages() {
        return messageRepo.findByDeletedFalseOrderByTimestampAsc();
    }

    @PostMapping("/upload-audio")
    @ResponseBody
    public Map<String, String> uploadAudio(@RequestParam("audio") MultipartFile file,
                                           @RequestParam("sender") String sender) throws IOException {
        String filename = "audio_" + System.currentTimeMillis() + ".webm";
        String path = "src/main/resources/static/audios/" + filename;
        file.transferTo(new File(path));

        String audioUrl = "/audios/" + filename;
        return Collections.singletonMap("audioUrl", audioUrl);
    }



}




