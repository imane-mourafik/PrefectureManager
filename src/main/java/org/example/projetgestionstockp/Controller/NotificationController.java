package org.example.projetgestionstockp.Controller;

import org.example.projetgestionstockp.Model.Notification;
import org.example.projetgestionstockp.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping("/notifications")
    public String afficherNotifications(Model model) {
        List<Notification> notifications = notificationRepository.findAll();
        model.addAttribute("notifications", notifications);
        return "notifications"; // JSP ou Thymeleaf
    }

    @PostMapping("/notifications/lue/{id}")
    public String marquerCommeLue(@PathVariable Long id) {
        Optional<Notification> notif = notificationRepository.findById(id);
        notif.ifPresent(n -> {
            n.setLue(true);
            notificationRepository.save(n);
        });
        return "redirect:/notifications";
    }
    @PostMapping("/notifications/supprimer/{id}")
    public String supprimerNotification(@PathVariable Long id) {
        notificationRepository.deleteById(id);
        return "redirect:/notifications"; // Reviens à la page des notifications
    }

}
