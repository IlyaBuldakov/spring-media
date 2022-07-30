package com.htc.domain.usecases.task;

import com.htc.domain.entities.failure.Failure;
import com.htc.domain.repositories.ContentsRepository;
import com.htc.domain.repositories.FilesRepository;
import com.htc.domain.repositories.TasksRepository;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Реализация сценария удаления задачи по идентификатору.
 */
@Component
@AllArgsConstructor
public class DeleteTask {

  TasksRepository tasksRepository;

  FilesRepository filesRepository;

  ContentsRepository contentsRepository;

  /**
   * Метод сценария.
   *
   * @param id Идентификатор задачи.
   * @return void.
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
    Set<String> urls = new HashSet<>();
    urls.addAll(contentsRepository.findRelevantToTaskContentUrl(id));
    urls.addAll(filesRepository.findRelevantToTaskFilesUrl(id));
    if (!urls.isEmpty()) {
      for (String url : urls) {
        new File(pathQualifier + url).delete();
      }
    }
  }
}
