package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UsersRepository;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Реализация сценария получения
 * пользователя по электронной почте.
 */
@Component
@AllArgsConstructor
public class GetUserByEmail {

  UsersRepository usersRepository;

  /**
   * Метод сценария.
   *
   * @param email Электронна почта.
   * @return Пользователь.
   */
  public CompletableFuture<Either<Failure, User>> execute(String email) {
    var expectedFailure = ValuesValidator.validateEmail(email);
    return expectedFailure == null
            ? usersRepository.getByEmail(email)
            : CompletableFuture.completedFuture(Either.left(expectedFailure));
  }
}
