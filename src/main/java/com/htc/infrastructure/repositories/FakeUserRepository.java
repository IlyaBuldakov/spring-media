package com.htc.infrastructure.repositories;

import com.github.javafaker.Faker;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
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
      User.create(
          1,
          faker.name().fullName(),
          faker.internet().emailAddress(),
          faker.internet().password(5, 17) + "1aA",
          faker.lorem().characters(40),
          Role.ADMIN
      ).get(),
      User.create(
          2,
          faker.name().fullName(),
          faker.internet().emailAddress(),
          faker.internet().password(5, 17) + "1aA",
          faker.lorem().characters(40),
          Role.MANAGER
      ).get(),
      User.create(
          3,
          faker.name().fullName(),
          faker.internet().emailAddress(),
          faker.internet().password(5, 17) + "1aA",
          faker.lorem().characters(40),
          Role.CONTENT_MAKER
      ).get()
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
  public Future<Either<Failure, Iterable<User>>> search(String query, Role role) {
    return null;
  }
}
