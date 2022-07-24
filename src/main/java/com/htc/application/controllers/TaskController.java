package com.htc.application.controllers;

import com.htc.application.dto.task.TaskRequestDto;
import com.htc.application.dto.task.TaskResponse;
import com.htc.domain.usecases.task.CreateTask;
import com.htc.domain.usecases.task.DeleteTaskById;
import com.htc.domain.usecases.task.GetAllTasks;
import com.htc.domain.usecases.task.GetTaskById;
import com.htc.domain.usecases.task.UpdateTask;
import com.htc.utility.Controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import java.util.Collection;
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
@SecurityRequirement(name = "JWT")
@Tags(@Tag(name = "Задачи"))
public class TaskController {
  private CreateTask createTask;
  private UpdateTask updateTask;
  private DeleteTaskById deleteTaskById;
  private GetTaskById getTaskById;
  private GetAllTasks getAllTasks;

  /**
   * Создаёт задачу.
   */
  @PostMapping
  @Async
  @Operation(summary = "Создать новую задачу")
  public void create(@RequestBody TaskRequestDto taskRequestDto) {
    Controllers.handleRequest(
            createTask,
            new CreateTask.Params(
                    taskRequestDto.getName(),
                    taskRequestDto.getContentType(),
                    taskRequestDto.getDescription(),
                    taskRequestDto.getAuthor(),
                    taskRequestDto.getExecutor(),
                    taskRequestDto.getDateExpired()
            ),
            null);
  }

  /**
   * Возвращает задачу.
   *
   * @param id Идентификатор задачи.
   * @return Задача.
   */
  @GetMapping(path = "/{id}")
  @Async
  @Operation(summary = "Получить задачу по идентификатору")
  public CompletableFuture<TaskResponse> get(@PathVariable Integer id) {
    return Controllers.handleRequest(
            getTaskById,
            id,
            TaskResponse::new);
  }

  /**
   * Возвращает список всех задач.
   *
   * @return Список задач.
   */
  @GetMapping
  @Operation(summary = "Получить список задач")
  public CompletableFuture<Collection<TaskResponse>> getAll() {
    return Controllers.handleRequest(
            getAllTasks,
            null,
            tasks -> tasks.stream()
                    .map(TaskResponse::new)
                    .collect(Collectors.toList()));
  }

  /**
   * Обновляет данные задачи.
   *
   * @param id Идентификатор задачи.
   */
  @PutMapping(path = "/{id}")
  @Operation(summary = "Изменить задачу по идентификатору")
  public void update(@PathVariable int id,
                     @RequestBody TaskRequestDto taskRequestDto) {
    Controllers.handleRequest(
            updateTask,
            new UpdateTask.Params(
                    id,
                    taskRequestDto.getName(),
                    taskRequestDto.getContentType(),
                    taskRequestDto.getDescription(),
                    taskRequestDto.getAuthor(),
                    taskRequestDto.getExecutor(),
                    taskRequestDto.getDateExpired()
            ),
            null);
  }

  /**
   * Удаляет задачу.
   *
   * @param id Идентификатор задачи.
   */
  @DeleteMapping(path = "/{id}")
  @Operation(summary = "Удалить задачу по идентификатор")
  public void delete(@PathVariable Integer id) {
    Controllers.handleRequest(
            deleteTaskById,
            id,
            null);
  }
}
