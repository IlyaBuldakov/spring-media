package com.htc.infrastructure.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UserRepository;
import io.vavr.control.Either;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория пользователей с ненастоящими данными.
 */
@Repository
public class FakeUserRepository implements UserRepository {
  private static final List<User> users = List.of(
          new User(
                  1,
                  "user@example.com",
                  "Passw0rd!",
                  "Иванов Иван",
                  new byte[]{},
                  Role.ADMIN
          ), new User(
                  2,
                  "user@example.com",
                  "Passw0rd!",
                  "Иванов Иван",
                  new byte[]{},
                  Role.CONTENT_MAKER
          ), new User(
                  3,
                  "user@example.com",
                  "Passw0rd!",
                  "Иванов Иван",
                  new byte[]{},
                  Role.MANAGER
          )
  );

  @Override
  public Future<Either<Failure, User>> create(User user) {
    return null;
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
  public Future<Either<Failure, User>> get(int id) {
    var user = users.stream().filter(u -> u.getId() == id).toList().get(0);
    return CompletableFuture.completedFuture(Either.right(user));
  }

  @Override
  public Future<Either<Failure, Iterable<User>>> getAll() {
    return CompletableFuture.completedFuture(Either.right(users));
  }

  @Override
  public Future<Either<Failure, Iterable<User>>> search(String query) {
    return null;
  }

  @Override
  public Future<Either<Failure, Iterable<User>>> search(String query, Role role) {
    return null;
  }
}
