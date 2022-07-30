package com.htc.application.services.impl;

import com.htc.application.dto.task.TaskRequest;
import com.htc.application.dto.task.TaskResponse;
import com.htc.application.services.ExceptionDtoResolver;
import com.htc.application.services.TasksService;
import com.htc.domain.usecases.task.CreateTask;
import com.htc.domain.usecases.task.DeleteTask;
import com.htc.domain.usecases.task.GetAllTasks;
import com.htc.domain.usecases.task.GetTaskById;
import com.htc.domain.usecases.task.UpdateTask;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Реализация {@link TasksService}.
 */
@AllArgsConstructor
@Service
public class TasksServiceImpl implements TasksService {

  CreateTask createTask;
  GetAllTasks getAllTasks;
  GetTaskById getTaskById;
  UpdateTask updateTask;
  DeleteTask deleteTask;

  /**
   * Получение всех задач.
   *
   * @return Список {@link TaskResponse представления} задач.
   */
  @Override
  public CompletableFuture<List<TaskResponse>> getAll() {
    return getAllTasks.execute()
            .thenApply(either ->
                    either.map(list -> list.parallelStream()
                                    .map(TaskResponse::new)
                                    .toList())
                            .getOrElseThrow(ExceptionDtoResolver::resolve));
  }

  /**
   * Получение задачи по идентификатору.
   *
   * @param id Идентификатор задачи.
   * @return {@link TaskResponse Представление} задачи.
   */
  @Override
  public CompletableFuture<TaskResponse> getById(String id) {
    return getTaskById.execute(id)
            .thenApply(either ->
                    either.map(TaskResponse::new).getOrElseThrow(ExceptionDtoResolver::resolve));
  }

  /**
   * Создание задачи.
   *
   * @param task {@link TaskRequest Представление} задачи (запрос).
   */
  @Override
  public CompletableFuture<Void> create(TaskRequest task) {
    return createTask.execute(
                    task.getName(), task.getType(), task.getDescription(),
                    task.getAuthor(), task.getExecutor(), task.getDateExpired())
            .thenApply(either -> {
              if (either.isLeft()) {
                throw ExceptionDtoResolver.resolve(either.getLeft());
              }
              return null;
            });
  }

  /**
   * Обновление задачи.
   *
   * @param user {@link TaskRequest Представление} задачи (запрос).
   * @param id   Идентификатор задачи.
   */
  @Override
  public CompletableFuture<Void> update(TaskRequest user, String id) {
    return updateTask.execute(id, user.getName(), user.getType(), user.getDescription(),
                    user.getAuthor(), user.getExecutor(), user.getDateExpired())
            .thenApply(either -> {
              if (either.isLeft()) {
                throw ExceptionDtoResolver.resolve(either.getLeft());
              }
              return null;
            });
  }

  /**
   * Удаление задачи по идентификатору.
   *
   * @param id Идентификатор задачи.
   */
  @Override
  public CompletableFuture<Void> delete(String id) {
    return deleteTask.execute(id)
            .thenApply(either -> {
              if (either.isLeft()) {
                throw ExceptionDtoResolver.resolve(either.getLeft());
              }
              return null;
            });
  }
}
