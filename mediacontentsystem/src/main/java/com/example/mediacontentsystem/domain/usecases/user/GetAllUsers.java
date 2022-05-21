package com.example.mediacontentsystem.domain.usecases.user;

import com.example.mediacontentsystem.domain.entities.failures.Failure;
import com.example.mediacontentsystem.domain.entities.user.User;
import com.example.mediacontentsystem.domain.repositories.UserRepository;
import com.example.mediacontentsystem.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;

/**
 * Получает всех пользователей.
 */
@AllArgsConstructor
public class GetAllUsers implements UseCase<Iterable<User>> {
  private final UserRepository repository;

  @Override
  public Future<Either<Failure, Iterable<User>>> execute() {
    return repository.getAll();
  }
}
