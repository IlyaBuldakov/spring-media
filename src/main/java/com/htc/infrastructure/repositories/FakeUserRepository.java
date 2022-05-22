package com.htc.infrastructure.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.user.UserRole;
import com.htc.domain.repositories.UserRepository;
import io.vavr.control.Either;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.springframework.stereotype.Component;

/**
 * Реализация репозитория пользователя с ложными данными.
 */
@Component
public class FakeUserRepository implements UserRepository {

  private static List<User> users = List.of(
          User.createTestUser(32),
          User.createTestUser(45),
          User.createTestUser(87)
  );

  @Override
  public Future<Either<Failure, User>> add(User user) {
    return null;
  }

  @Override
  public Future<Either<Failure, User>> get(int id) {
    return CompletableFuture.completedFuture(Either.right(users.get(id)));
  }

  @Override
  public Future<Either<Failure, Iterable<User>>> getAll() {
    return CompletableFuture.completedFuture(Either.right(users));
  }

  @Override
  public Future<Either<Failure, User>> update(User user) {
    return null;
  }

  @Override
  public Future<Either<Failure, Void>> delete(int id) {
    return null;
  }

  @Override
  public Future<Either<Failure, Iterable<User>>> search(String query) {
    return null;
  }

  @Override
  public Future<Either<Failure, Iterable<User>>> search(String query, UserRole role) {
    return null;
  }
}
