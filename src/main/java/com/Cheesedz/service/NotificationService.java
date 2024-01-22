package com.Cheesedz.service;

import com.Cheesedz.controller.NotificationController;
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

    public ResponseEntity<ResponseObject> getAllNotifications(Long userID) {
        List<Notification> foundNotifications = notificationRepository.findByUserID(userID);
        return foundNotifications.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Get all user's notifications successfully", foundNotifications)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find any notifications", "")
                );
    }

    public ResponseEntity<ResponseObject> findById(Long id, Long userID) {
        Notification foundNotification = notificationRepository.findById(id).get();
        return foundNotification.getUserID().equals(userID) ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Get notification successfully", foundNotification)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Notification doesn't belong to user", "")
                );
    }

    public ResponseEntity<ResponseObject> insertNotification(Notification newNotification, Long userID) {
        Optional<Notification> foundNotifications = notificationRepository.findById(newNotification.getId());
        if (!foundNotifications.isPresent()) {
            logger.info("Failed to insert data: " + newNotification);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResponseObject("failed", "Notification name already existed", "")
            );
        } else {
            newNotification.setUserID(userID);
            logger.info("Insert data successfully. " + newNotification);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Insert notification successfully", notificationRepository.save(newNotification))
            );
        }
    }

    public ResponseEntity<ResponseObject> updateNotification(Notification newNotification, Long id, Long userID) {
        Notification foundNotification = notificationRepository.findById(id).get();
        if (foundNotification.getUserID().equals(userID)) {
            foundNotification.setTitle(newNotification.getTitle());
            foundNotification.setDetailContent(newNotification.getDetailContent());
            foundNotification.setUserID(newNotification.getUserID());
            notificationRepository.save(foundNotification);
            logger.info("Update data successfully. " + newNotification);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Update notification successfully", foundNotification)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                new ResponseObject("failed", "Notification doesn't not belong to user ", "")
        );
    }

    public ResponseEntity<ResponseObject> deleteNotification(Long id, Long userID) {
        Optional<Notification> foundNotification = notificationRepository.findById(id);
        if (!foundNotification.isPresent()) {
            logger.info("Failed to delete data");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Cannot find notification to delete", "")
            );
        } else {
            if (foundNotification.get().getUserID().equals(userID)) {
                logger.info("Delete data successfully");
                notificationRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Delete notification successfully", "")
                );
            } else {
                logger.info("Failed to delete data");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Notification doesn't not belong to user ", "")
                );
            }
        }
    }
}
