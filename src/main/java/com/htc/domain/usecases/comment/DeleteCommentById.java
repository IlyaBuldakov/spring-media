package com.htc.domain.usecases.comment;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValues;
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
    var idEither = Id.create(id);

    if (idEither.isRight()) {
      return repository.delete(idEither.get());
    }

    var invalidValues = new InvalidValues();
    invalidValues.addInvalidValue(idEither.getLeft());
    return CompletableFuture.completedFuture(Either.left(invalidValues));

  }
}
