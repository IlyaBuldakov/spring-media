package com.example.mediacontentsystem.domain.usecases.user;

import com.example.mediacontentsystem.domain.entities.failures.Failure;
import com.example.mediacontentsystem.domain.entities.user.User;
import com.example.mediacontentsystem.domain.repositories.UserRepository;
import com.example.mediacontentsystem.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;

/**
 * Получает пользователя по идентификатору.
 */
@AllArgsConstructor
public final class GetUserById implements UseCase<Integer, User> {
  private final UserRepository repository;

  @Override
  public Future<Either<Failure, User>> execute(Integer param) {
    return repository.get(param);
  }
}
