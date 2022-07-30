package com.htc.domain.usecases.task;

import com.htc.domain.entities.failure.Failure;
import com.htc.domain.entities.failure.Unauthorized;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.user.Role;
import com.htc.domain.repositories.TasksRepository;
import com.htc.domain.usecases.UseCaseHelper;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Реализация сценария получения задачи по идентификатору.
 */
@Component
@AllArgsConstructor
public class GetTaskById {

  TasksRepository tasksRepository;

  /**
   * Роли, которым разрешено данное действие.
   */
  private final Role[] permittedRoles = new Role[]{
          Role.ADMIN,
          Role.MANAGER,
          Role.CONTENT_MAKER
  };

  /**
   * Метод сценария.
   *
   * @param id Идентификатор задачи.
   * @return Задача.
   */
  public CompletableFuture<Either<Failure, Task>> execute(Set<String> permissions,
                                                          String id) {
    var expectedFailure = ValuesValidator.validateStringId(id);
    if (expectedFailure != null) {
      return CompletableFuture.completedFuture(Either.left(expectedFailure));
    }
    return UseCaseHelper.hasRolePermissions(permissions, permittedRoles)
            ? tasksRepository.getById(Integer.parseInt(id))
            : CompletableFuture.completedFuture(Either.left(Unauthorized.FORBIDDEN));
  }
}
