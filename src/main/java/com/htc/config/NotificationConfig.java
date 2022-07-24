package com.htc.config;

import com.htc.domain.repositories.NotificationRepository;
import com.htc.domain.usecases.notification.DeleteNotificationById;
import com.htc.domain.usecases.notification.GetAllNotifications;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Инициализация сценариев использования сущности уведомления.
 */
@Configuration
public class NotificationConfig {

  @Bean
  public DeleteNotificationById deleteNotificationById(NotificationRepository repository) {
    return new DeleteNotificationById(repository);
  }

  @Bean
  public GetAllNotifications getAllNotifications(NotificationRepository repository) {
    return new GetAllNotifications(repository);
  }
}
