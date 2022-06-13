package com.htc.infrastructure.repositories;

import com.github.javafaker.Faker;
import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.ContentFormat;
import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.files.File;
import com.htc.domain.entities.files.FileFormat;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.task.TaskStatus;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.TaskRepository;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.springframework.stereotype.Component;

/**
 * Реализация репозитория задач с ненастоящими данными.
 */
@Component
public class FakeTaskRepository implements TaskRepository {
  private static final Faker faker = Faker.instance(new Locale("ru"));
  private static final LocalDateTime dateCreated = LocalDateTime.now();
  private static final LocalDateTime dateExpired = dateCreated.plusDays(1);
  private static final List<File> files = List.of(
      File.upload(
          1,
          faker.file().fileName(),
          dateCreated,
          FileFormat.DOC,
          "api/files",
          1
      ).get()
  );
  private static final List<User> users = List.of(
      User.create(
          1,
          faker.name().fullName(),
          faker.internet().emailAddress(),
          faker.internet().password(8, 12),
          faker.lorem().characters(40),
          Role.ADMIN
      ).get(),
      User.create(
          2,
          faker.name().fullName(),
          faker.internet().emailAddress(),
          faker.internet().password(8, 12),
          faker.lorem().characters(40),
          Role.CONTENT_MAKER
      ).get()
  );
  private static final List<Content> contents = List.of(
      Content.upload(
          1,
          ContentType.PHOTO,
          faker.file().fileName(),
          dateCreated,
          users.get(1),
          ContentFormat.JPG,
          "api/contents",
          "api/previews",
          1
      ).get()
  );
  private static final List<Comment> comments = List.of(
      Comment.create(
          1,
          dateCreated,
          users.get(0),
          "Комментарий к задаче",
          1
      ).get()
  );
  private static final List<Task> tasks = List.of(
      Task.create(
          1,
          "Подготовка изображения.",
          ContentType.PHOTO,
          "Нужно сделать фото для задачи.",
          files.get(0),
          users.get(0),
          users.get(1),
          dateCreated,
          dateExpired,
          contents.get(0),
          comments.get(0),
          TaskStatus.IN_WORK
      ).get(),
      Task.create(
          2,
          "Подготовка видео.",
          ContentType.VIDEO,
          "Нужно сделать видео для задачи.",
          files.get(0),
          users.get(0),
          users.get(1),
          dateCreated,
          dateExpired,
          contents.get(0),
          comments.get(0),
          TaskStatus.IN_WORK
      ).get()
  );

  @Override
  public Future<Either<Failure, Task>> create(Task task) {
    return null;
  }

  @Override
  public Future<Either<Failure, Task>> update(Task task) {
    return null;
  }

  @Override
  public Future<Either<Failure, Void>> delete(int id) {
    return null;
  }

  @Override
  public Future<Either<Failure, Task>> get(int id) {
    var task = tasks.stream()
        .filter(u -> u.getId() == id)
        .toList().get(0);
    return CompletableFuture.completedFuture(Either.right(task));
  }

  @Override
  public Future<Either<Failure, Iterable<Task>>> getAll() {
    return CompletableFuture.completedFuture(Either.right(tasks));
  }
}
