package com.example.mediacontentsystem.domain.usecases.user;

import com.example.mediacontentsystem.domain.entities.failures.Failure;
import com.example.mediacontentsystem.domain.repositories.UserRepository;
import com.example.mediacontentsystem.domain.usecases.UseCaseUsingParams;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;

/**
 * Удаляет пользователя.
 */
@AllArgsConstructor
public class DeleteUserById implements UseCaseUsingParams<Integer, Void> {
  private final UserRepository repository;

  @Override
  public Future<Either<Failure, Void>> execute(Integer param) {
    return repository.delete(param);
  }
}
