package com.htc.application.controllers;

import com.htc.application.dto.task.TaskDto;
import com.htc.application.dto.user.UserResponse;
import com.htc.domain.usecases.task.CreateTask;
import com.htc.domain.usecases.task.DeleteTaskById;
import com.htc.domain.usecases.task.GetAllTasks;
import com.htc.domain.usecases.task.GetTaskById;
import com.htc.domain.usecases.task.UpdateTask;
import com.htc.domain.usecases.user.CreateUser;
import com.htc.domain.usecases.user.DeleteUserById;
import com.htc.domain.usecases.user.GetAllUsers;
import com.htc.domain.usecases.user.GetUserById;
import com.htc.domain.usecases.user.UpdateUser;
import com.htc.utility.Controllers;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с пользователями.
 */
@RestController
@RequestMapping(path = "api/tasks")
@AllArgsConstructor
public class TaskController {
  private CreateTask createTask;
  private UpdateTask updateTask;
  private DeleteTaskById deleteTaskById;
  private GetTaskById getTaskById;
  private GetAllTasks getAllTasks;

  /**
   * Создаёт пользователя.
   */
  @PostMapping
  public void create() {
  }

  /**
   * Возвращает пользователя.
   *
   * @param id Идентификатор пользователя.
   * @return Пользователь.
   */
  @GetMapping(path = "/{id}")
  @Async
  public CompletableFuture<TaskDto> get(@PathVariable Integer id) {
    return Controllers.handleRequest(
            getTaskById,
            id,
            TaskDto::new);
  }

  /**
   * Возвращает список всех пользователей.
   *
   * @return Список пользователей.
   */
  @GetMapping
  public CompletableFuture<Collection<TaskDto>> getAll() {
    return Controllers.handleRequest(
            getAllTasks,
            null,
            tasks -> tasks.stream()
                    .map(TaskDto::new)
                    .collect(Collectors.toList()));
  }

  /**
   * Обновляет данные пользователя.
   *
   * @param id Идентификатор пользователя.
   */
  @PutMapping(path = "/{id}")
  public void update(@PathVariable Integer id) {

  }

  /**
   * Удаляет пользователя.
   *
   * @param id Идентификатор пользователя.
   */
  @DeleteMapping(path = "/{id}")
  public void delete(@PathVariable Integer id) {
    Controllers.handleRequest(
            deleteTaskById,
            id,
            null);
  }
}