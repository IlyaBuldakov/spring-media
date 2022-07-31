package finalproject.application.controllers;

import finalproject.application.dto.failures.BadRequestDto;
import finalproject.application.dto.failures.FailureConverter;
import finalproject.application.dto.failures.NotFoundDto;
import finalproject.application.dto.task.TaskDto;
import finalproject.application.dto.task.TaskListDto;
import finalproject.application.dto.task.TaskRequestDto;
import finalproject.application.services.AuthService;
import finalproject.application.services.TaskService;
import finalproject.application.services.UserService;
import finalproject.domain.entities.content.ContentType;
import finalproject.domain.entities.task.Task;
import finalproject.domain.entities.task.TaskStatus;
import finalproject.domain.entities.user.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  /**
   * Редактирование задачи.
   *
   * @param id id задачи
   * @param taskdto запрос с новыми параметрами задачи
   * @return измененную задачу
   * @throws ExecutionException ошибки многопоточности
   * @throws InterruptedException ошибки многопоточности
   */
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
    Task updatedTask = Task.create(taskdto.getName(),
                    ContentType.getContentTypeByName(taskdto.getType()),
                                  taskdto.getDescription(), author, contentMaker,
                                  taskdto.getDateExpired())
                            .getOrElseThrow(BadRequestDto::new);
    updatedTask.setTaskStatus(TaskStatus.getTaskStatusByName(taskdto.getTaskStatus()));
    return taskService.editTask(updatedTask, id, autorizedUserId)
            .thenApply(either -> either.getOrElseThrow(failure ->
                    FailureConverter.convert(failure)));

  }

  /**
   * Получение задачи.
   *
   * @param id id задачи
   * @return задачу в обертке dto
   */
  @ApiOperation(value = "", authorizations = { @Authorization(value = "Bearer") })
  @GetMapping("/{id}")
  public CompletableFuture<TaskDto> getTaskById(@PathVariable int id) {
    return taskService.getTaskById(id)
            .thenApply(either -> either.getOrElseThrow(failure ->
                    FailureConverter.convert(failure)))
            .thenApply(TaskDto::new);
  }

  /**
   * Получение списка задач.
   *
   * @return ленту задач в зависимости от роли пользователя
   */
  @ApiOperation(value = "", authorizations = { @Authorization(value = "Bearer") })
  @GetMapping
  public CompletableFuture<TaskListDto[]> getTasks() {
    int autorizedUserId = authService.getId();
    return taskService.getAllTasks(autorizedUserId)
            .thenApply(list -> list.stream()
                    .map(TaskListDto::new)
                    .toList()
                    .toArray(new TaskListDto[0]));
  }

  /**
   * Удаление задачи.
   *
   * @param id задачи
   * @return пустой ответ
   */
  @ApiOperation(value = "", authorizations = { @Authorization(value = "Bearer") })
  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  @DeleteMapping("/{id}")
  public CompletableFuture<Void> deleteTaskById(@PathVariable int id) {
    int autorizedUserId = authService.getId();
    return taskService.deleteTask(id, autorizedUserId)
            .thenApply(either -> either.getOrElseThrow(
                    failure -> FailureConverter.convert(failure)));
  }


}
