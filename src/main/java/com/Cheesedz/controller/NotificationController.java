package com.Cheesedz.controller;

import com.Cheesedz.model.Notification;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/user/{userId}/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllNotifications(@PathVariable(name = "userId") Long userId) {
        return notificationService.getAllNotifications(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getNotification(@PathVariable(name = "userId") Long userId,
                                                          @PathVariable(name = "id") Long id) {
        return notificationService.findById(id, userId);
    }

    @PostMapping("/insert")
    //@PreAuthorize("hasRole('SHOP_ADMIN') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> insertNotification(@RequestBody Notification newNotification,
                                                             @PathVariable(name = "userId") Long userId) {
        return notificationService.insertNotification(newNotification, userId);
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('SHOP_ADMIN') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> updateNotification(@RequestBody Notification newNotification,
                                     @PathVariable(name = "userId") Long userId, @PathVariable(name = "id") Long id) {
        return notificationService.updateNotification(newNotification, id, userId);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('SHOP_ADMIN') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> deleteNotification(@PathVariable(name = "userId") Long userId,
                                                             @PathVariable(name = "id") Long id) {
        return notificationService.deleteNotification(id, userId);
    }
}
