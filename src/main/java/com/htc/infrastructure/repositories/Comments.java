package com.htc.infrastructure.repositories;

import com.htc.infrastructure.models.comment.CommentModel;
import java.util.concurrent.CompletableFuture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * JPA репозиторий комментария.
 */
public interface Comments extends JpaRepository<CommentModel, Long> {
  @Async
  CompletableFuture<CommentModel> findById(long id);
}
