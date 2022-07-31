package com.htc.config;

import com.htc.domain.repositories.NotificationRepository;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.service.NotificationService;
import com.htc.domain.usecases.NotificationUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {

  @Bean
  public NotificationUseCase.GetAll getAllNotifications(
      NotificationRepository repository,
      UserRepository userRepository
  ) {
    return new NotificationUseCase.GetAll(repository, userRepository);
  }

  @Bean
  public NotificationService notificationService(NotificationUseCase.GetAll getAll) {
    return new NotificationService(getAll);
  }
}
