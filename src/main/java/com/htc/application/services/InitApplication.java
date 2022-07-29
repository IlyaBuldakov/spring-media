package com.htc.application.services;

import com.htc.domain.entities.user.Role;
import com.htc.infrastructure.models.user.UserModel;
import com.htc.infrastructure.repositories.Users;
import java.util.concurrent.ExecutionException;
import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Инициализация после внедрения зависимостей.
 */
@Component
@RequiredArgsConstructor
public class InitApplication {
  private final @NotNull Users users;
  private static final String ADMIN_NAME = "admin";
  private static final String ADMIN_PASSWORD = "notPassword42";
  private static final String ADMIN_EMAIL = "mailru@mail.com";
  private static final String ADMIN_IMAGE = "image==";

  /**
   * Создание администратора.
   */
  @PostConstruct
  public void init() throws ExecutionException, InterruptedException {
    UserModel user = new UserModel(0L,
            ADMIN_NAME,
            ADMIN_PASSWORD,
            ADMIN_EMAIL,
            ADMIN_IMAGE,
            Role.ADMIN.getName());
    users.save(user);
  }
}
