package com.Cheesedz.service;

import com.Cheesedz.controller.NotificationController;
import com.Cheesedz.controller.ProductController;
import com.Cheesedz.model.Notification;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);
    @Autowired
    private NotificationRepository notificationRepository;

    public ResponseEntity<ResponseObject> getAllNotifications() {
        List<Notification> foundNotifications = notificationRepository.findAll();
        return foundNotifications.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Get all notifications successfully", foundNotifications)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find any notifications", "")
                );
    }

    public ResponseEntity<ResponseObject> findById(Long id) {
        Optional<Notification> foundNotification = notificationRepository.findById(id);
        return foundNotification.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query notification successfully", foundNotification)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find notification with id = " + id, "")
                );
    }

    public ResponseEntity<ResponseObject> insertNotification(Notification newNotification) {
        Optional<Notification> foundNotifications = notificationRepository.findById(newNotification.getId());
        if (!foundNotifications.isPresent()) {
            logger.info("Failed to insert data: " + newNotification);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResponseObject("failed", "Notification name already existed", "")
            );
        } else {
            logger.info("Insert data successfully. " + newNotification);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Insert notification successfully", notificationRepository.save(newNotification))
            );
        }
    }

    public ResponseEntity<ResponseObject> updateNotification(Notification newNotification, Long id) {
        Notification updatedNotification = notificationRepository.findById(id).map(
                notification -> {
                    notification.setTitle(newNotification.getTitle());
                    notification.setDetailContent(newNotification.getDetailContent());
                    notification.setUserID(newNotification.getUserID());
                    return notificationRepository.save(notification);
                }
        ).orElseGet(()-> notificationRepository.save(newNotification));
        logger.info("Update data successfully. " + newNotification);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update notification successfully", updatedNotification)
        );
    }

    public ResponseEntity<ResponseObject> deleteNotification(Long id) {
        boolean existed = notificationRepository.existsById(id);
        if (existed) {
            logger.info("Delete data successfully");
            notificationRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete notification successfully", "")
            );
        } else {
            logger.info("Failed to delete data");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Cannot find notification to delete", "")
            );
        }
    }
}
