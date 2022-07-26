package com.htc.infrastructure.repositories;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.Task;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.TaskRepository;
import com.htc.infrastructure.models.TaskModel;
import com.htc.utility.Results;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория задач.
 */
@Repository
public class TaskRepositoryImpl implements TaskRepository {
  @Autowired
  Tasks tasks;

  @Override
  public CompletableFuture<Either<Failure, Task>> create(
      String name, Content.Type type, String description,
      int authorId, int executorId, LocalDateTime dateCreated,
      LocalDateTime dateExpired, Task.Status status) {
    var task = new TaskModel(
        name, type, description, authorId,
        executorId, dateCreated, dateExpired, status);
    return Results.succeed(tasks.save(task));
  }

  @Override
  public CompletableFuture<Either<Failure, Task>> update(
      String name, Content.Type type, String description,
      int[] fileId, int authorId, int executorId, LocalDateTime dateExpired,
      int[] contentsId, int[] commentsId, Task.Status status) {
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, Void>> delete(Id id) {
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, Task>> get(Id id) {
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, Collection<Task>>> getAll() {
    return null;
  }

  /**
   * ORM для доступа к данным задач в СУБД.
   */
  public interface Tasks extends JpaRepository<TaskModel, Integer> {

  }
}