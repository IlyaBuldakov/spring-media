package ru.kiryanovid.application.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kiryanovid.application.dto.errors.BadRequestDto;
import ru.kiryanovid.application.dto.errors.InternalServerErrorDto;
import ru.kiryanovid.application.dto.errors.NotAuthorizedDto;
import ru.kiryanovid.application.dto.errors.NotFoundDto;
import ru.kiryanovid.application.dto.task.TaskDto;
import ru.kiryanovid.application.dto.task.TaskListDto;
import ru.kiryanovid.application.dto.task.TaskRequestDto;
import ru.kiryanovid.domain.entity.errors.InvalidValue;
import ru.kiryanovid.domain.entity.errors.NotAuthorized;
import ru.kiryanovid.domain.entity.task.Task;
import ru.kiryanovid.domain.usecases.task.CreateTask;
import ru.kiryanovid.domain.usecases.task.DeleteTaskById;
import ru.kiryanovid.domain.usecases.task.GetAllTasks;
import ru.kiryanovid.domain.usecases.task.GetTaskById;
import ru.kiryanovid.domain.usecases.task.UpdateTask;
import ru.kiryanovid.domain.usecases.user.GetUserById;


/**
 * Контроллер задач.
 */
@RestController
@RequestMapping(path = "api/tasks")
@SecurityRequirement(name = "JWT")
@Tags(@Tag(name = "Задачи"))
@RequiredArgsConstructor
public class TaskController {
  private final GetAllTasks getAllTasks;
  private final CreateTask createTask;
  private final GetTaskById getTaskById;
  private final UpdateTask updateTask;
  private final DeleteTaskById deleteTaskById;
  private final GetUserById getUserById;

  /**
  * Создание новой задачи.
  *
  * @param taskRequestDto представление задачи.
  *
  * @throws ExecutionException Ошибка выполнения
  * @throws InterruptedException Ошибка прерывания
  */
  @PostMapping
  @Async
  @Operation(summary = "Создание новой задачи")
  public void create(@RequestBody TaskRequestDto taskRequestDto)
          throws ExecutionException, InterruptedException {
    var author = getUserById.execute(taskRequestDto.getAuthor()).get().get();
    var executor = getUserById.execute(taskRequestDto.getExecutor()).get().get();
    var task = Task.create(0,
                taskRequestDto.getName(),
                taskRequestDto.getContentType(),
                taskRequestDto.getDescription(),
                null,
                author,
                executor,
                LocalDateTime.now(),
                taskRequestDto.getDateExpired(),
                null,
                null,
                null).getOrElseThrow(failure -> switch (failure) {
                  case InvalidValue invalidValue ->
                          new BadRequestDto(HttpStatus.BAD_REQUEST, invalidValue.getMessage());
                  case NotAuthorized notAuthorized ->
                          new NotAuthorizedDto(HttpStatus.NOT_FOUND, notAuthorized.getMessage());
                  default -> new InternalServerErrorDto();
                });
    createTask.execute(task);
  }

  /**
  * Обновление задачи.
  *
  * @param taskRequestDto представление задачи
  *
  * @param id идентификатор
  * @throws ExecutionException Ошибка выполнения
  * @throws InterruptedException Ошибка прерывания
  */

  @PutMapping(path = "/{id}")
  @Async
  @Operation(summary = "Обновление задачи по идентификатору")
  public void updateTask(@RequestBody TaskRequestDto taskRequestDto, @PathVariable Integer id)
          throws ExecutionException, InterruptedException {
    var author = getUserById.execute(taskRequestDto.getAuthor()).get().get();
    var executor = getUserById.execute(taskRequestDto.getExecutor()).get().get();
    var updatedTask = Task.create(id,
                taskRequestDto.getName(),
                taskRequestDto.getContentType(),
                taskRequestDto.getDescription(),
                null,
                author,
                executor,
                null,
                taskRequestDto.getDateExpired(),
                null,
                null,
                null).get();
    updateTask.execute(updatedTask);
  }

  /**
  * Удаление задачи.
  *
  * @param id идентификатор
  */

  @DeleteMapping(path = "/{id}")
  @Async
  @Operation(summary = "Удаление задачи по идентификатору")
  public void deleteTaskById(@PathVariable Integer id) {
    deleteTaskById.execute(id);
  }

  /**
  * Получение задачи.
  *
  * @param id идентификатор
  *
  * @return задача
  * @throws ExecutionException Ошибка выполнения
  * @throws InterruptedException Ошибка прерывания
  */
  @GetMapping(path = "/{id}")
  @Async
  @Operation(summary = "Получение задачи по идентификатору")
  public CompletableFuture<TaskDto> getTaskById(@PathVariable Integer id)
          throws ExecutionException, InterruptedException {
    var result = getTaskById.execute(id);
    if (result.get().isLeft()) {
      var temp = result.get().getLeft();
      throw new NotFoundDto(HttpStatus.NOT_FOUND, temp.getMessage());
    }
    return CompletableFuture.completedFuture(new TaskDto(result.get().get()));
  }

  /**
  * Получение списка задач.
  *
  * @return Список задач
  *
  * @throws ExecutionException Ошибка выполнения
  * @throws InterruptedException Ошибка прерывания
  */
  @GetMapping
  @Async
  @Operation(summary = "Получение списка всех задач")
  public List<TaskListDto> getAll() throws ExecutionException, InterruptedException {
    var taskList = getAllTasks.execute(null)
                .get()
                .getOrElseThrow(failure -> switch (failure) {
                  case NotAuthorized notAuthorized ->
                  new NotAuthorizedDto(HttpStatus.UNAUTHORIZED, notAuthorized.getMessage());
                  default -> new InternalServerErrorDto(failure);
                });
    List<TaskListDto> dtoList = new ArrayList<>();
    for (Task task : taskList) {
      dtoList.add(new TaskListDto(task));
    }
    return dtoList;
  }
}

