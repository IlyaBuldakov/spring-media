package com.htc.infrastructure.repositories;

import com.github.javafaker.Faker;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.user.UserRole;
import com.htc.domain.repositories.UserRepository;
import io.vavr.control.Either;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.springframework.stereotype.Component;

/**
 * Реализация репозитория пользователей с ненастоящими данными.
 */
@Component
public class FakeUserRepository implements UserRepository {
  private static final Faker faker = Faker.instance(new Locale("ru"));
  private static final List<User> users = List.of(
      new User(
          1,
          faker.name().fullName(),
          faker.internet().emailAddress(),
          faker.internet().password(8, 12),
          faker.lorem().characters(40),
          new UserRole(1, UserRole.RoleType.ADMIN)
      ),
      new User(
          2,
          faker.name().fullName(),
          faker.internet().emailAddress(),
          faker.internet().password(8, 12),
          faker.lorem().characters(40),
          new UserRole(1, UserRole.RoleType.MANAGER)
      ),
      new User(
          3,
          faker.name().fullName(),
          faker.internet().emailAddress(),
          faker.internet().password(8, 12),
          faker.lorem().characters(40),
          new UserRole(1, UserRole.RoleType.CONTENT_MAKER)
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
    var user = users.stream()
        .filter(u -> u.getId() == id)
        .toList().get(0);
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
  public Future<Either<Failure, Iterable<User>>> search(String query, UserRole role) {
    return null;
  }
}
