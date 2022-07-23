package com.htc.infrastructure.repositories;

import com.htc.infrastructure.models.notification.NotificationModel;
import java.util.concurrent.CompletableFuture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * JPA репозиторий уведомления.
 */
public interface Notifications extends JpaRepository<NotificationModel, Long> {
  @Async
  CompletableFuture<NotificationModel> findById(long id);
}
