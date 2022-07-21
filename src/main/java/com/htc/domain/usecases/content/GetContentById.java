package com.htc.domain.usecases.content;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.repositories.ContentRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;

/**
 * Сценарий получения контента по ее идентификатору.
 */
@AllArgsConstructor
public final class GetContentById implements UseCase<Integer, Content> {
  private final ContentRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Content>> execute(Integer id) {
    var idEither = Id.create(id);
    if (idEither.isRight()) {
      return repository.get(idEither.get());
    }

    var invalidValues = new InvalidValues();
    invalidValues.addInvalidValue(idEither.getLeft());
    return CompletableFuture.completedFuture(Either.left(invalidValues));
  }
}


