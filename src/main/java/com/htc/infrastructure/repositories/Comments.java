package com.htc.infrastructure.repositories;

import com.htc.infrastructure.models.comment.CommentModel;
import java.util.concurrent.CompletableFuture;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA репозиторий комментария.
 */
public interface Comments extends JpaRepository<CommentModel, Long> {
  CompletableFuture<CommentModel> findById(long id);
}
