package com.htc.domain.usecases.comment;

import com.htc.domain.entities.Comment;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.CommentRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;

/**
 * Сценарий обновления комментария.
 */
@AllArgsConstructor
public final class UpdateComment implements UseCase<Comment, Comment> {
  private final CommentRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Comment>> execute(Comment comment) {
    return repository.update(comment);
  }
}
