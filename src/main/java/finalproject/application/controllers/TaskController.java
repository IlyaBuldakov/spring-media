package finalproject.application.controllers;

import finalproject.application.dto.failures.BadRequestDto;
import finalproject.application.dto.failures.NotFoundDto;
import finalproject.application.dto.task.TaskRequestDto;
import finalproject.application.services.TaskService;
import finalproject.application.services.UserService;
import finalproject.domain.entities.content.ContentType;
import finalproject.domain.entities.task.Task;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер задач.
 */
@AllArgsConstructor
@RestController
@RequestMapping("api/tasks")
public class TaskController {

  UserService userService;
  TaskService taskService;

  /**Выполняет запрос на создание задачи.
   *
   * @param taskdto запрос на создание задачи.
   * @return задачу
   * @throws ExecutionException при асинхронном выполнении
   * @throws InterruptedException при асинхронном выполнении
   */
  @ApiOperation(value = "", authorizations = { @Authorization(value = "Bearer") })
  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  @PutMapping
  public CompletableFuture<Task> createTask(@RequestBody TaskRequestDto taskdto)
          throws ExecutionException, InterruptedException {
    userService.getUserById(taskdto.getAuthorId())
            .thenApply(either -> either.getOrElseThrow(failure -> new NotFoundDto(failure)));
    int authorId = taskdto.getAuthorId();
    userService.getUserById(taskdto.getContentMakerId())
            .thenApply(either -> either.getOrElseThrow(failure -> new NotFoundDto(failure)));
    int contentMakerId = taskdto.getContentMakerId();
    Task task = Task.create(taskdto.getName(), ContentType.getContentTypeByName(taskdto.getType()),
            taskdto.getDescription(), authorId, contentMakerId,
            taskdto.getDateExpired()).getOrElseThrow(failure -> new BadRequestDto(failure));
    task.setDateCreated(LocalDateTime.now());
    return taskService.createNewTask(task).thenApply(either -> either.get());


  }

}
