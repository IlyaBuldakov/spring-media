package com.htc.infrastructure.repositories;

import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.failure.Failure;
import com.htc.domain.entities.failure.NotFound;
import com.htc.domain.entities.task.Task;
import com.htc.domain.repositories.TasksRepository;
import com.htc.infrastructure.jpa.TasksJpaRepository;
import com.htc.infrastructure.mappers.TaskMapper;
import com.htc.util.Results;
import io.vavr.control.Either;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

/**
 * Реализация репозитория для работы с задачами.
 */
@Component
@AllArgsConstructor
public class TasksRepositoryImpl implements TasksRepository {

  /**
   * JPA-репозиторий.
   */
  TasksJpaRepository tasksJpaRepository;

  /**
   * Создание задачи.
   *
   * @param name        Имя задачи.
   * @param type        Тип задачи.
   * @param description Описание задачи.
   * @param author      Автор задачи.
   * @param executor    Исполнитель задачи.
   * @param dateExpired Дата выполнения задачи (срок выполнения).
   * @return void.
   */
  @Override
  public CompletableFuture<Either<Failure, Void>> create(String name, ContentType type,
                                                         String description, int author,
                                                         int executor, LocalDate dateExpired) {
    tasksJpaRepository.save(new TaskMapper(name, type, description, author, executor, dateExpired));
    return Results.nullValue();
  }

  /**
   * Получение задачи по идентификатору.
   *
   * @param id Идентификатор задачи.
   * @return Задача.
   */
  @Override
  public CompletableFuture<Either<Failure, Task>> getById(int id) {
    var task = tasksJpaRepository.findById(id);
    if (task.isPresent()) {
      return Results.success(task.get());
    }
    return Results.fail(NotFound.TASK);
  }

  /**
   * Получение списка всех задач.
   *
   * @return Список задач.
   */
  @Override
  public CompletableFuture<Either<Failure, List<Task>>> getAll() {
    return Results.success(new ArrayList<>(tasksJpaRepository.findAll()));
  }

  /**
   * Обновление задачи.
   *
   * @param id          Идентификатор задачи.
   * @param name        Имя задачи.
   * @param type        Тип задачи.
   * @param description Описание задачи.
   * @param author      Автор задачи.
   * @param executor    Исполнитель задачи
   * @param dateExpired Дата выполнения задачи (срок выполнения).
   * @return void.
   */
  @Override
  public CompletableFuture<Either<Failure, Void>> update(int id, String name, ContentType type,
                                                         String description, int author,
                                                         int executor, LocalDate dateExpired) {
    var task = getOptionalById(id);
    if (task.isPresent()) {
      updateTask(id, name, type, description, author, executor,
              task.get().getDateCreated(), dateExpired);
    } else {
      updateTask(id, name, type, description, author,
              executor, LocalDate.now(), dateExpired);
    }
    return Results.nullValue();
  }

  /**
   * Вспомогательный метод для упрощения кода.
   * (при обновлении dateCreated отличается).
   *
   * @param id          Идентификатор задачи.
   * @param name        Имя задачи.
   * @param type        Тип задачи.
   * @param description Описание задачи.
   * @param author      Автор задачи.
   * @param executor    Исполнитель задачи.
   * @param dateCreated Дата создания задачи.
   * @param dateExpired Дата исполнения.
   */
  private void updateTask(int id, String name, ContentType type, String description,
                          int author, int executor, LocalDate dateCreated, LocalDate dateExpired) {
    tasksJpaRepository.save(new TaskMapper(
            id, name, type, description,
            author, executor, dateCreated, dateExpired));
  }

  /**
   * Вспомогательный метод получения задачи в {@link Optional}
   * без асинхронного выполнения ({@link CompletableFuture}).
   *
   * @param id Идентификатор задачи.
   * @return Задача в {@link Optional}.
   */
  private Optional<TaskMapper> getOptionalById(int id) {
    return tasksJpaRepository.findById(id);
  }

  /**
   * Удаление задачи по идентификатору.
   *
   * @param id Идентификатор задачи.
   * @return void.
   */
  @Override
  public CompletableFuture<Either<Failure, Void>> deleteById(int id) {
    try {
      tasksJpaRepository.deleteById(id);
    } catch (EmptyResultDataAccessException exception) {
      return Results.fail(NotFound.TASK);
    }
    return Results.nullValue();
  }
}
