package com.htc.domain.usecases.comment;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.CommentRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;

/**
 * Сценарий удаления комментария по его идентификатору.
 */
@AllArgsConstructor
public final class DeleteCommentById implements UseCase<Integer, Void> {
  private final CommentRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Void>> execute(Integer id) {
    return repository.delete(id);
  }
}
