package com.htc.infrastructure.repositories;

import com.github.javafaker.Faker;
import com.htc.domain.entities.failures.AlreadyExists;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.entities.failures.RepositoryFailure;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UserRepository;
import io.vavr.control.Either;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория пользователей с ненастоящими данными.
 */
@Repository
public class FakeUserRepository implements UserRepository {
  private static final double SUCCESS_CHANCE = 0.8;
  private static final Faker faker = Faker.instance(new Locale("ru"));
  private static final List<User> users = new ArrayList<>();

  static {
    var roles = Role.values();
    var count = new Random().nextInt(10);

    while (count-- >= 0) {
      users.add(User.create(
                      new Random().nextInt(255),
                      faker.name().fullName(),
                      faker.internet().emailAddress(),
                      faker.internet().password(5, 17) + "1aA",
                      faker.lorem().characters(40),
                      roles[new Random().nextInt(roles.length)])
              .get());
    }
  }

  @Override
  public CompletableFuture<Either<Failure, User>> create(User user) {
    if (Math.random() > SUCCESS_CHANCE) {
      return CompletableFuture.completedFuture(Either.left(RepositoryFailure.DEFAULT_MESSAGE));
    }

    if (users.contains(user)) {
      return CompletableFuture.completedFuture(Either.left(AlreadyExists.DEFAULT_MESSAGE));
    }

    users.add(user);

    return CompletableFuture.completedFuture(Either.right(user));
  }

  @Override
  public CompletableFuture<Either<Failure, User>> update(User user) {

    if (Math.random() > SUCCESS_CHANCE) {
      return CompletableFuture.completedFuture(Either.left(RepositoryFailure.DEFAULT_MESSAGE));
    }

    var userToUpdate = users.stream()
            .filter(u -> u.getId() == user.getId())
            .findFirst();


    if (userToUpdate.isEmpty()) {
      return CompletableFuture.completedFuture(Either.left(NotFound.DEFAULT_MESSAGE));
    }

    users.set(users.indexOf(userToUpdate.get()), user);

    return CompletableFuture.completedFuture(Either.right(user));
  }

  @Override
  public CompletableFuture<Either<Failure, Void>> delete(int id) {
    if (Math.random() > SUCCESS_CHANCE) {
      return CompletableFuture.completedFuture(Either.left(RepositoryFailure.DEFAULT_MESSAGE));
    }

    users.removeIf(user -> user.getId() == id);
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, User>> get(int id) {
    if (Math.random() > SUCCESS_CHANCE) {
      return CompletableFuture.completedFuture(Either.left(RepositoryFailure.DEFAULT_MESSAGE));
    }

    var result = users.stream().filter(u -> u.getId() == id).toList();

    if (result.size() == 0) {
      return CompletableFuture.completedFuture(Either.left(NotFound.DEFAULT_MESSAGE));
    }

    return CompletableFuture.completedFuture(Either.right(result.get(0)));
  }

  @Override
  public CompletableFuture<Either<Failure, Collection<User>>> getAll() {
    return Math.random() > SUCCESS_CHANCE
            ? CompletableFuture.completedFuture(Either.left(RepositoryFailure.DEFAULT_MESSAGE))
            : CompletableFuture.completedFuture(Either.right((users)));
  }
}
