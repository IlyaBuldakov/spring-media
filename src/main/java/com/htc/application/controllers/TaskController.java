package com.htc.application.controllers;

import com.htc.application.dtos.task.TaskRequest;
import com.htc.application.dtos.task.TaskResponse;
import com.htc.application.dtos.task.TaskUpdateRequest;
import com.htc.domain.usecases.task.AddTask;
import com.htc.domain.usecases.task.DeleteTaskById;
import com.htc.domain.usecases.task.GetAllTasks;
import com.htc.domain.usecases.task.GetTaskById;
import com.htc.domain.usecases.task.UpdateTaskById;
import com.htc.utility.ControllerHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер задачи.
 */
@AllArgsConstructor
@RestController
@SecurityRequirement(name = "JWT")
@Tags(@Tag(name = "Задачи"))
@RequestMapping(path = "api/tasks")
public class TaskController {
  private AddTask addTask;
  private UpdateTaskById updateTaskById;
  private DeleteTaskById deleteTaskById;
  private GetTaskById getTaskById;
  private GetAllTasks getAllTasks;

  /**
   * Добавление задачи.
   */
  @Operation(summary = "Добавить новую задачу.")
  @PostMapping
  public void add(@RequestBody TaskRequest taskRequest) {
    ControllerHelper.customRequest(
            addTask,
            new AddTask.Params(
                    taskRequest.getName(), "name",
                    taskRequest.getType(), "type",
                    taskRequest.getDescription(), "description",
                    taskRequest.getAuthorId(), "author",
                    taskRequest.getExecutorId(), "executor",
                    taskRequest.getDateExpired(), "dateExpired"
            ),
            null
    );
  }

  /**
   * Получение задачи.
   *
   * @param id идентификатор
   * @return task задача
   */
  @Operation(summary = "Получить задачу по идентификатору.")
  @GetMapping(path = "/{id}")
  public CompletableFuture<TaskResponse> get(@PathVariable Long id) {
    return ControllerHelper.customRequest(
            getTaskById,
            new GetTaskById.Params(id, "id"),
            TaskResponse::new
    );
  }

  /**
   * Получение ленты задач.
   *
   * @return list лента задач
   */
  @Operation(summary = "Получить ленту задач.")
  @GetMapping
  public CompletableFuture<List<TaskResponse>> getAll() {
    return ControllerHelper.customRequest(
            getAllTasks,
            null,
            tasks -> tasks.stream()
                    .map(TaskResponse::new)
                    .collect(Collectors.toList())
    );
  }

  /**
   * Обновление задачи.
   *
   * @param id идентификатор
   */
  @Operation(summary = "Обновить задачу по идентификатору.")
  @PutMapping(path = "/{id}")
  public void update(@PathVariable Long id, @RequestBody TaskUpdateRequest taskUpdateRequest) {
    ControllerHelper.customRequest(
            updateTaskById,
            new UpdateTaskById.Params(
                    id, "id",
                    taskUpdateRequest.getName(), "name",
                    taskUpdateRequest.getType(), "type",
                    taskUpdateRequest.getDescription(), "description",
                    taskUpdateRequest.getFileId(), "file",
                    taskUpdateRequest.getAuthorId(), "author",
                    taskUpdateRequest.getExecutorId(), "executor",
                    taskUpdateRequest.getDateCreated(), "dateCreated",
                    taskUpdateRequest.getDateExpired(), "dateExpired",
                    taskUpdateRequest.getContentId(), "content",
                    taskUpdateRequest.getCommentId(), "comment",
                    taskUpdateRequest.getStatus(), "status"
            ),
            TaskResponse::new
    );
  }

  /**
   * Удаление задачи.
   *
   * @param id идентификатор
   */
  @Operation(summary = "Удалить задачу по идентификатору.")
  @DeleteMapping(path = "/{id}")
  public CompletableFuture<Void> delete(@PathVariable Long id) {
    return ControllerHelper.customRequest(
            deleteTaskById,
            new DeleteTaskById.Params(id, "id"),
            null
    );
  }
}
