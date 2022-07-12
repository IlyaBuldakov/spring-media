package com.htc.domain.usecases.user;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий получения пользователя по его идентификатору.
 */
@Component
@AllArgsConstructor
public final class GetUserById implements UseCase<Integer, User> {
  private final UserRepository repository;

  @Override
  public CompletableFuture<Either<Failure, User>> execute(Integer id) {
    var idEither = Id.create(id);
    if (idEither.isRight()) {
      return repository.get(idEither.get());
    }

    var invalidValues = new InvalidValues();
    invalidValues.addInvalidValue(idEither.getLeft());
    return CompletableFuture.completedFuture(Either.left(invalidValues));
  }
}


/*
* @AllArgsConstructor
public final class DeleteUserById implements UseCase<Integer, Void> {
  private final UserRepository repository;

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
*/