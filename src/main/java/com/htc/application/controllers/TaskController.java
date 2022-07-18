package com.htc.application.controllers;

import com.htc.domain.entities.Task;
import com.htc.domain.usecases.task.CreateTask;
import com.htc.domain.usecases.task.DeleteTaskById;
import com.htc.domain.usecases.task.GetAllTasks;
import com.htc.domain.usecases.task.GetTaskById;
import com.htc.domain.usecases.task.UpdateTaskById;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с задачами.
 */
@RestController
@RequestMapping(path = "api/tasks")
@AllArgsConstructor
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
  public void create() {
    throw new UnsupportedOperationException("Метод не реализован");
  }

  /**
   * Обновляет данные задачи.
   *
   * @param id Идентификатор задачи.
   */
  @PutMapping(path = "/{id}")
  public void update(@PathVariable int id) {
    throw new UnsupportedOperationException("Метод не реализован");
  }

  /**
   * Удаляет задачу.
   *
   * @param id Идентификатор задачи.
   */
  @DeleteMapping(path = "/{id}")
  public void delete(@PathVariable int id) {
    throw new UnsupportedOperationException("Метод не реализован");
  }

  /**
   * Возвращает задачу.
   *
   * @param id Идентификатор задачи.
   * @return Задача.
   * @throws ExecutionException Ошибка выполения запроса.
   * @throws InterruptedException Ошибка выполения запроса.
   */
  @GetMapping(path = "/{id}")
  public Task get(@PathVariable int id) throws ExecutionException, InterruptedException {
    throw new UnsupportedOperationException("Метод не реализован");
  }

  /**
   * Возвращает список всех задач.
   *
   * @return Список задач.
   * @throws ExecutionException Ошибка выполения запроса.
   * @throws InterruptedException Ошибка выполения запроса.
   */
  @GetMapping
  public Iterable<Task> getAll() throws ExecutionException, InterruptedException {
    throw new UnsupportedOperationException("Метод не реализован");
  }
}
