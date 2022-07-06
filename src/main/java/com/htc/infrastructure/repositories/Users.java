package com.htc.infrastructure.repositories;

import com.htc.infrastructure.models.user.UserModel;
import java.util.concurrent.CompletableFuture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * JPA репозиторий пользователя.
 */
public interface Users extends JpaRepository<UserModel, Long> {
  @Async
  CompletableFuture<UserModel> findById(long id);
}
