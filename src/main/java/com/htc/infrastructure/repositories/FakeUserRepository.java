package com.htc.infrastructure.repositories;

import com.github.javafaker.Faker;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.entities.failures.RepositoryFailure;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UserRepository;
import io.vavr.control.Either;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.springframework.stereotype.Component;

/**
 * Реализация репозитория пользователя с ложными данными.
 */
@Component
public class FakeUserRepository implements UserRepository {
  private static final Faker faker = Faker.instance(new Locale("ru"));
  private static final List<User> users = List.of(
          User.add(
                  32,
                  faker.name().fullName(),
                  "gTeggstiag1",
                  faker.internet().emailAddress(),
                  faker.lorem().characters(40) + "==",
                  Role.ADMIN
          ).get(),
          User.add(
                  45,
                  faker.name().fullName(),
                  "gTeggstiag2",
                  faker.internet().emailAddress(),
                  faker.lorem().characters(40) + "==",
                  Role.MANAGER
          ).get(),
          User.add(
                  87,
                  faker.name().fullName(),
                  "gTeggstiag3",
                  faker.internet().emailAddress(),
                  faker.lorem().characters(40) + "==",
                  Role.CONTENT_MAKER
          ).get()
  );

  @Override
  public Future<Either<Failure, User>> add(User user) {
    return null;
  }

  @Override
  public Future<Either<Failure, User>> get(int id) {
    // тестовая реализация бизнес-логики
    if (id < 1) {
      return CompletableFuture.completedFuture(Either.left(NotFound.DEFAULT_MESSAGE));
    }
    if (new Random().nextBoolean()) {
      return CompletableFuture.completedFuture(Either.left(RepositoryFailure.DEFAULT_MESSAGE));
    }
    return CompletableFuture.completedFuture(Either.right(
            users.stream().filter(user -> user.getId() == id)
                    .toList()
                    .get(0)));
  }

  @Override
  public Future<Either<Failure, Iterable<User>>> getAll() {
    // тестовая реализация бизнес-логики
    return new Random().nextBoolean()
            ? CompletableFuture.completedFuture(Either.right(users))
            : CompletableFuture.completedFuture(Either.left(RepositoryFailure.DEFAULT_MESSAGE));
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
  public Future<Either<Failure, Iterable<User>>> search(String query, Role role) {
    return null;
  }
}
