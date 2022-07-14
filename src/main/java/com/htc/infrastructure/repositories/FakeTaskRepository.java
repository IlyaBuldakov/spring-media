package com.htc.infrastructure.repositories;

import com.github.javafaker.Faker;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.entities.failures.RepositoryFailure;
import com.htc.domain.entities.tasks.Task;
import com.htc.domain.repositories.TaskRepository;
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
public class FakeTaskRepository implements TaskRepository {
  private static final double SUCCESS_CHANCE = 0.8;
  private static final Faker faker = Faker.instance(new Locale("ru"));
  private static final List<Task> tasks = new ArrayList<>();

  static {
    var taskStatus = Task.TaskStatus.values();
    var count = new Random().nextInt(10);

    while (count-- >= 0) {
      tasks.add(Task.create(
                      new Random().nextInt(255),
                      null,
                      null,
                      null,
                      null,
                      null,
                      null,
                      null,
                      null,
                      null,
                      null,
                      taskStatus[new Random().nextInt(taskStatus.length)])
              .get());
    }
  }

  @Override
  public CompletableFuture<Either<Failure, Task>> create(Task task) {
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, Task>> update(Task task) {
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, Void>> delete(int id) {
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, Task>> get(int id) {
    if (Math.random() > SUCCESS_CHANCE) {
      return CompletableFuture.completedFuture(Either.left(RepositoryFailure.DEFAULT_MESSAGE));
    }

    var result = tasks.stream().filter(u -> u.getId() == id).toList();

    if (result.size() == 0) {
      return CompletableFuture.completedFuture(Either.left(NotFound.DEFAULT_MESSAGE));
    }

    return CompletableFuture.completedFuture(Either.right(result.get(0)));
  }

  @Override
  public CompletableFuture<Either<Failure, Collection<Task>>> getAll() {
    return Math.random() > SUCCESS_CHANCE
            ? CompletableFuture.completedFuture(Either.left(RepositoryFailure.DEFAULT_MESSAGE))
            : CompletableFuture.completedFuture(Either.right((tasks)));
  }
}
