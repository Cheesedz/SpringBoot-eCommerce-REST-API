package com.Cheesedz.service;

import com.Cheesedz.model.Notification;
import com.Cheesedz.payload.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface NotificationService {
    ResponseEntity<ResponseObject> getAllNotifications(Long userID);
    ResponseEntity<ResponseObject> findById(Long id, Long userID);
    ResponseEntity<ResponseObject> insertNotification(Notification newNotification, Long userID);
    ResponseEntity<ResponseObject> updateNotification(Notification newNotification, Long id, Long userID);
    ResponseEntity<ResponseObject> deleteNotification(Long id, Long userID);
}
