package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UsersRepository;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@AllArgsConstructor
public class GetUserByEmail {

  UsersRepository usersRepository;

  public CompletableFuture<Either<Failure, User>> execute(String email) {
    var expectedFailure = ValuesValidator.validateEmail(email);
    return expectedFailure == null
            ? usersRepository.getByEmail(email)
            : CompletableFuture.completedFuture(Either.left(expectedFailure));
  }
}
