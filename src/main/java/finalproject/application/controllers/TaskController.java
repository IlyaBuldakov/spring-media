package finalproject.application.controllers;

import finalproject.application.dto.failures.BadRequestDto;
import finalproject.application.dto.failures.InternalServerErrorDto;
import finalproject.application.dto.failures.NotFoundDto;
import finalproject.application.dto.task.TaskDto;
import finalproject.application.dto.task.TaskRequestDto;
import finalproject.application.services.AuthService;
import finalproject.application.services.TaskService;
import finalproject.application.services.UserService;
import finalproject.domain.entities.content.ContentType;
import finalproject.domain.entities.failures.BadRequest;
import finalproject.domain.entities.failures.NotFound;
import finalproject.domain.entities.task.Task;
import finalproject.domain.entities.task.TaskStatus;
import finalproject.domain.entities.user.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер задач.
 */
@AllArgsConstructor
@RestController
@RequestMapping("api/tasks")
public class TaskController {

  UserService userService;
  TaskService taskService;

  AuthService authService;

  /**Выполняет запрос на создание задачи.
   *
   * @param taskDto запрос на создание задачи.
   * @return задачу
   * @throws ExecutionException при асинхронном выполнении
   * @throws InterruptedException при асинхронном выполнении
   */
  @ApiOperation(value = "", authorizations = { @Authorization(value = "Bearer") })
  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  @PutMapping
  public CompletableFuture<Task> createTask(@RequestBody TaskRequestDto taskDto)
          throws ExecutionException, InterruptedException {
    int autorizedUserId = authService.getId();
    User author = userService.getUserById(taskDto.getAuthorId())
            .thenApply(either -> either.getOrElseThrow(NotFoundDto::new)).get();
    User contentMaker = userService.getUserById(taskDto.getContentMakerId())
            .thenApply(either -> either.getOrElseThrow(NotFoundDto::new)).get();
    Task task = Task.create(taskDto.getName(), ContentType.getContentTypeByName(taskDto.getType()),
            taskDto.getDescription(), author, contentMaker,
            taskDto.getDateExpired()).getOrElseThrow(BadRequestDto::new);
    task.setDateCreated(LocalDateTime.now());
    task.setTaskStatus(TaskStatus.INWORK);
    return taskService.createNewTask(task, autorizedUserId).thenApply(Either::get);

  }

  @ApiOperation(value = "", authorizations = { @Authorization(value = "Bearer") })
  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  @PutMapping("/{id}")
  public CompletableFuture<Task> editTask(@PathVariable int id, @RequestBody TaskRequestDto taskdto)
          throws ExecutionException, InterruptedException {
    int autorizedUserId = authService.getId();
    User author = userService.getUserById(taskdto.getAuthorId())
            .thenApply(either -> either.getOrElseThrow(NotFoundDto::new))
            .get();
    User contentMaker = userService.getUserById(taskdto.getContentMakerId())
            .thenApply(either -> either.getOrElseThrow(NotFoundDto::new))
            .get();
    Task updatedTask = Task.create(taskdto.getName(), ContentType.getContentTypeByName(taskdto.getType()),
                                  taskdto.getDescription(), author, contentMaker,
                                  taskdto.getDateExpired())
                            .getOrElseThrow(BadRequestDto::new);
    updatedTask.setTaskStatus(TaskStatus.getTaskStatusByName(taskdto.getTaskStatus()));
    return taskService.editTask(updatedTask, id, autorizedUserId).thenApply(
            either -> either.getOrElseThrow(failure -> {
              if (failure instanceof BadRequest) {
                return new BadRequestDto(failure);
              }
              if (failure instanceof NotFound) {
                return new NotFoundDto(failure);
              }
              return new InternalServerErrorDto(failure);
            }));

  }

  @ApiOperation(value = "", authorizations = { @Authorization(value = "Bearer") })
  @GetMapping("/{id}")
  public CompletableFuture<Task> getTaskById(@PathVariable int id) {
    return taskService.getTaskById(id).thenApply(either -> either.getOrElseThrow(failure -> {
      if (failure instanceof BadRequest) {
        return new BadRequestDto(failure);
      }
      if (failure instanceof NotFound) {
        return new NotFoundDto(failure);
      }
      return new InternalServerErrorDto(failure);
            }));

  }


}
