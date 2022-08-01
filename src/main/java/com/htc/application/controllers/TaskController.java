package com.htc.application.controllers;

import com.htc.application.dto.task.TaskRequest;
import com.htc.application.dto.task.TaskResponse;
import com.htc.domain.usecases.task.CreateTask;
import com.htc.domain.usecases.task.DeleteTaskById;
import com.htc.domain.usecases.task.GetAllTasks;
import com.htc.domain.usecases.task.GetTaskById;
import com.htc.domain.usecases.task.UpdateTaskById;
import com.htc.utility.Controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с задачами.
 */
@RestController
@RequestMapping(path = "api/tasks")
@AllArgsConstructor
@Tags(@Tag(name = "Задачи"))
public class TaskController {
  private CreateTask createTask;
  private UpdateTaskById updateTask;
  private DeleteTaskById deleteTaskById;
  private GetAllTasks getAllTasks;
  private GetTaskById getTaskById;

  /**
   * Создаёт задачу.
   */
  @PostMapping
  @Operation(summary = "Создать новую задачу")
  public void create(@RequestBody TaskRequest taskRequest) {
    Controllers.handleRequest(
        createTask,
        new CreateTask.Params(
            taskRequest.name, "name",
            taskRequest.type, "type",
            taskRequest.description, "description",
            taskRequest.authorId, "authorId",
            taskRequest.executorId, "executorId",
            taskRequest.dateExpired, "dateExpired",
            taskRequest.status, "status"
            ),
        null);
  }

  /**
   * Обновляет данные задачи.
   *
   * @param id Идентификатор задачи.
   */
  @PutMapping(path = "/{id}")
  @Operation(summary = "Изменить задачу по идентификатору")
  @Async
  public void update(@PathVariable int id, @RequestBody TaskRequest taskRequest) {
    Controllers.handleRequest(
        updateTask,
        new UpdateTaskById.Params(
            id, "id",
            taskRequest.name, "name",
            taskRequest.type, "type",
            taskRequest.description, "description",
            taskRequest.authorId, "authorId",
            taskRequest.executorId, "executorId",
            taskRequest.dateExpired, "dateExpired",
            taskRequest.status, "status"),
        null
    );
  }

  /**
   * Удаляет задачу.
   *
   * @param id Идентификатор задачи.
   */
  @DeleteMapping(path = "/{id}")
  @Operation(summary = "Удалить задачу по идентификатору")
  @Async
  public void delete(@PathVariable int id) {
    Controllers.handleRequest(
        deleteTaskById,
        new DeleteTaskById.Params(id, "id"),
        null
    );
  }

  /**
   * Получает задачу по идентификатору.
   *
   * @param id Идентификатор задачи.
   * @return Представление сущности задача.
   */
  @GetMapping(path = "/{id}")
  @Operation(summary = "Получить задачу по идентификатору")
  @Async
  public CompletableFuture<TaskResponse> get(@PathVariable int id) {
    return Controllers.handleRequest(
        getTaskById,
        new GetTaskById.Params(id, "id"),
        TaskResponse::new);
  }

  /**
   * Получает список задач.
   *
   * @return Список представлений сущности задач.
   */
  @GetMapping
  @Operation(summary = "Получить список задач")
  @Async
  public CompletableFuture<Iterable<TaskResponse>> getAll() {
    return Controllers.handleRequest(
        getAllTasks,
        null,
        tasks -> tasks.stream()
            .map(TaskResponse::new)
            .collect(Collectors.toList()));
  }
}
