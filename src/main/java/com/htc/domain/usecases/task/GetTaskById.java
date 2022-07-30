package com.htc.domain.usecases.task;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.task.Task;
import com.htc.domain.repositories.TasksRepository;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Реализация сценария получения задачи по идентификатору.
 */
@Component
@AllArgsConstructor
public class GetTaskById {

  /**
   * Поле для внедрения реализации из infrastructure layer.
   */
  TasksRepository tasksRepository;

  /**
   * Метод сценария.
   *
   * @param id Идентификатор задачи.
   * @return Задача.
   */
  public CompletableFuture<Either<Failure, Task>> execute(String id) {
    var expectedFailure = ValuesValidator.validateStringId(id);
    return expectedFailure == null ? tasksRepository.getById(Integer.parseInt(id))
            : CompletableFuture.completedFuture(Either.left(expectedFailure));
  }
}
