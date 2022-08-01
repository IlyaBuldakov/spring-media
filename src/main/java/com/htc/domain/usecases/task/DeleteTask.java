package com.htc.domain.usecases.task;

import com.htc.domain.entities.failure.Failure;
import com.htc.domain.entities.failure.Forbidden;
import com.htc.domain.entities.user.Role;
import com.htc.domain.repositories.ContentsRepository;
import com.htc.domain.repositories.FilesRepository;
import com.htc.domain.repositories.TasksRepository;
import com.htc.domain.usecases.UseCaseHelper;
import com.htc.util.Results;
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
   * Роли, которым разрешено данное действие.
   */
  private final Role[] permittedRoles = new Role[]{
      Role.ADMIN
  };

  /**
   * Метод сценария.
   *
   * @param id Идентификатор задачи.
   * @return void.
   */
  public CompletableFuture<Either<Failure, Void>> execute(Set<String> permissions,
                                                          String id) {
    var expectedFailure = ValuesValidator.validateStringId(id);
    if (expectedFailure != null) {
      return Results.fail(expectedFailure);
    }
    if (UseCaseHelper.hasRolePermissions(permissions, permittedRoles)) {
      clearRelevantStaticResources(Integer.parseInt(id));
      return tasksRepository.deleteById(Integer.parseInt(id));
    }
    return Results.fail(new Forbidden());
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
