package org.example.projetgestionstockp.Service;

import org.example.projetgestionstockp.Model.Notification;
import org.example.projetgestionstockp.Model.Personne;
import org.example.projetgestionstockp.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public void envoyerNotification(String message, Personne admin) {
        Notification notif = new Notification(false, message, new Date(), admin);
        notificationRepository.save(notif);
    }

    public List<Notification> getNotificationsNonLues() {
        return notificationRepository.findByLueFalse();
    }

    public List<Notification> getToutesLesNotifications() {
        return notificationRepository.findAll();
    }

    public void marquerCommeLue(Long id) {
        Notification notif = notificationRepository.findById(id).orElse(null);
        if (notif != null) {
            notif.setLue(true);
            notificationRepository.save(notif);
        }
    }
    public void ajouterNotification(String message, Personne administrateur) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setLue(false);
        notification.setDate(new Date());
        notification.setAdministrateur(administrateur); // peut être null si pas d’admin connecté
        notificationRepository.save(notification);
    }
}