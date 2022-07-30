package com.htc.domain.usecases.task;

import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.TasksRepository;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Реализация сценария обновления задачи.
 */
@Component
@AllArgsConstructor
public class UpdateTask {

  /**
   * Поле для внедрения реализации из infrastructure layer.
   */
  TasksRepository tasksRepository;

  /**
   * Метод сценария.
   *
   * @param id          Идентификатор задачи.
   * @param name        Имя задачи.
   * @param type        Тип задачи.
   * @param description Описание задачи.
   * @param author      Автор задачи.
   * @param executor    Исполнитель задачи.
   * @param dateExpired Дата выполнения задачи (срок выполнения).
   * @return void.
   */
  public CompletableFuture<Either<Failure, Void>> execute(String id, String name,
                                                          ContentType type, String description,
                                                          String author, String executor,
                                                          LocalDate dateExpired) {
    var expectedFailure = ValuesValidator.checkTaskFields(id, name, description, author, executor);
    return expectedFailure == null
            ? tasksRepository.update(Integer.parseInt(id), name, type, description,
                    Integer.parseInt(author), Integer.parseInt(executor), dateExpired)
            : CompletableFuture.completedFuture(Either.left(expectedFailure));
  }
}
