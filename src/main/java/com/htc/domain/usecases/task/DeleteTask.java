package com.htc.domain.usecases.task;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.FilesRepository;
import com.htc.domain.repositories.TasksRepository;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import java.io.File;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Реализация сценария удаления задачи по идентификатору.
 */
@Component
@AllArgsConstructor
public class DeleteTask {

  /**
   * Поле для внедрения реализации из infrastructure layer.
   */
  TasksRepository tasksRepository;

  /**
   * Поле для внедрения реализации из infrastructure layer.
   */
  FilesRepository filesRepository;

  /**
   * Метод сценария.
   *
   * @param id Идентификатор задачи.
   * @return void
   */
  public CompletableFuture<Either<Failure, Void>> execute(String id) {
    var expectedFailure = ValuesValidator.validateStringId(id);
    if (expectedFailure != null) {
      return CompletableFuture.completedFuture(Either.left(expectedFailure));
    }
    clearRelevantStaticResources(Integer.parseInt(id));
    return tasksRepository.deleteById(Integer.parseInt(id));
  }

  /**
   * Отчистка статических ресурсов,
   * прикрепленных к задаче, при её удалении.
   *
   * @param id Идентификатор задачи.
   */
  private void clearRelevantStaticResources(int id) {
    String pathQualifier = "src/main/webapp/";
    var fileUrls = filesRepository.findRelevantToTaskFilesUrl(id);
    if (!fileUrls.isEmpty()) {
      for (String url : fileUrls) {
        new File(pathQualifier + url).delete();
      }
    }
  }
}
