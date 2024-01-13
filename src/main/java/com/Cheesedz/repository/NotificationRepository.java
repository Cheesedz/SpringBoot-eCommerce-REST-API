package com.Cheesedz.repository;

import com.Cheesedz.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @NonNull
    @Query("SELECT n FROM notification n WHERE n.id = ?1")
    Optional<Notification> findByNotificationId(@NonNull Long notificationID);
}
