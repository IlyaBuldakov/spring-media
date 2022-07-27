package com.htc.infrastructure.repositories;

import com.htc.infrastructure.models.notification.NotificationModel;
import java.util.concurrent.CompletableFuture;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA репозиторий уведомления.
 */
public interface Notifications extends JpaRepository<NotificationModel, Long> {
  CompletableFuture<NotificationModel> findById(long id);
}
