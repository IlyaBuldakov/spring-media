package com.example.mediacontentsystem.domain.usecases.user;

import com.example.mediacontentsystem.domain.entities.failures.Failure;
import com.example.mediacontentsystem.domain.entities.user.User;
import com.example.mediacontentsystem.domain.repositories.UserRepository;
import com.example.mediacontentsystem.domain.usecases.UseCaseUsingParams;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;

/**
 * Фильтрует пользователей по имени.
 */
@AllArgsConstructor
public class FilterUsersByName implements UseCaseUsingParams<String, Iterable<User>> {
  private final UserRepository repository;

  @Override
  public Future<Either<Failure, Iterable<User>>> execute(String param) {
    return repository.filter(param);
  }
}
