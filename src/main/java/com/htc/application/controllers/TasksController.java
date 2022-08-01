package com.htc.application.controllers;

import com.htc.application.dto.task.TaskRequest;
import com.htc.application.dto.task.TaskResponse;
import com.htc.application.services.TasksService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
@AllArgsConstructor
@RequestMapping("/api/tasks")
public class TasksController {

  TasksService tasksService;

  /**
   * Получение задачи по идентификатору.
   *
   * @param id Идентификатор задачи.
   * @return Представление задачи (ответ).
   */
  @GetMapping("/{id}")
  @Async
  public CompletableFuture<TaskResponse> getById(@PathVariable String id) {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    return tasksService.getById(
            securityContext.getAuthentication().getAuthorities(), id);
  }

  /**
   * Получение списка всех задач.
   *
   * @return Список представления задач (ответ).
   */
  @GetMapping
  @Async
  public CompletableFuture<List<TaskResponse>> getAll() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    return tasksService.getAll(
            securityContext.getAuthentication().getAuthorities());
  }

  /**
   * Создание задачи.
   *
   * @param taskRequest Представление задачи (запрос).
   * @return void.
   */
  @PostMapping
  @Async
  public CompletableFuture<Void> create(@RequestBody TaskRequest taskRequest) {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    return tasksService.create(
            securityContext.getAuthentication().getAuthorities(), taskRequest);
  }

  /**
   * Обновление задачи.
   *
   * @param taskRequest Представление задачи (запрос).
   * @param id          Идентификатор задачи.
   * @return void.
   */
  @PutMapping("/{id}")
  @Async
  public CompletableFuture<Void> update(@RequestBody TaskRequest taskRequest,
                                        @PathVariable String id) {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    return tasksService.update(
            securityContext.getAuthentication().getAuthorities(), taskRequest, id);
  }

  /**
   * Удаление задачи по идентификатору.
   *
   * @param id Идентификатор задачи.
   * @return void.
   */
  @DeleteMapping("/{id}")
  @Async
  public CompletableFuture<Void> delete(@PathVariable("id") String id) {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    return tasksService.delete(
            securityContext.getAuthentication().getAuthorities(), id);
  }
}
