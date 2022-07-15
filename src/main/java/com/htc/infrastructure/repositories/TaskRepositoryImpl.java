package com.htc.infrastructure.repositories;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.comments.Comment;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.entities.files.File;
import com.htc.domain.entities.tasks.Task;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.TaskRepository;
import com.htc.infrastructure.models.TaskModel;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория пользователей.
 */
@Repository
public class TaskRepositoryImpl implements TaskRepository {
  @Autowired
  Tasks tasks;

  @Override
  public CompletableFuture<Either<Failure, Task>> create(
          Task.Name name,
          ContentType contentType,
          Task.Description description,
          Collection<File> files,
          User author,
          User executor,
          LocalDateTime dateCreated,
          LocalDateTime dateExpired,
          Collection<Content> contents,
          Collection<Comment> comments,
          Task.TaskStatus taskStatus) {
    var task = new TaskModel(name, contentType, description, files, author, executor,
            dateCreated, dateExpired, contents, comments, taskStatus);
    return CompletableFuture.completedFuture(Either.right(tasks.save(task)));
  }

  @Override
  public CompletableFuture<Either<Failure, Task>> update(
          Id id,
          Task.Name name,
          ContentType contentType,
          Task.Description description,
          Collection<File> files,
          User author,
          User executor,
          LocalDateTime dateCreated,
          LocalDateTime dateExpired,
          Collection<Content> contents,
          Collection<Comment> comments,
          Task.TaskStatus taskStatus) {
    var task = new TaskModel(id, name, contentType, description, files, author, executor,
            dateCreated, dateExpired, contents, comments, taskStatus);
    return CompletableFuture.completedFuture(Either.right(tasks.save(task)));
  }

  @Override
  public CompletableFuture<Either<Failure, Void>> delete(Id id) {
    tasks.deleteById(id.getValue());
    return CompletableFuture.completedFuture(Either.right(null));
  }

  @Override
  public CompletableFuture<Either<Failure, Task>> get(Id id) {
    var task = tasks.findById(id.getValue());
    return task.isPresent()
            ? CompletableFuture.completedFuture(Either.right(task.get()))
            : CompletableFuture.completedFuture(Either.left(NotFound.DEFAULT_MESSAGE));
  }

  @Override
  public CompletableFuture<Either<Failure, Collection<Task>>> getAll() {
    return CompletableFuture.completedFuture(Either.right(new ArrayList<>(tasks.findAll())));
  }

  /**
   * ORM для доступа к данным пользователей в СУБД.
   */
  public interface Tasks extends JpaRepository<TaskModel, Integer> {
  }
}