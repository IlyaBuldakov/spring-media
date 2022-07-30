package com.htc.domain.usecases.task;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.task.Task;
import com.htc.domain.repositories.TasksRepository;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Реализация сценария получения всех задач.
 */
@Component
@AllArgsConstructor
public class GetAllTasks {

  /**
   * Поле для внедрения реализации из infrastructure layer.
   */
  private TasksRepository tasksRepository;

  /**
   * Метод сценария.
   *
   * @return Список задач.
   */
  public CompletableFuture<Either<Failure, List<Task>>> execute() {
    return tasksRepository.getAll();
  }
}
