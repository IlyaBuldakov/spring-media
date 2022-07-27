package com.htc.infrastructure.repositories;

import com.htc.infrastructure.models.user.UserModel;
import java.util.concurrent.CompletableFuture;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA репозиторий пользователя.
 */
public interface Users extends JpaRepository<UserModel, Long> {
  CompletableFuture<UserModel> findById(long id);

  CompletableFuture<UserModel> findByEmail(String email);
}
