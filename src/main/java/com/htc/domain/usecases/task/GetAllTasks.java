package com.htc.domain.usecases.task;

import com.htc.domain.entities.failure.Failure;
import com.htc.domain.entities.failure.Forbidden;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.user.Role;
import com.htc.domain.repositories.TasksRepository;
import com.htc.domain.usecases.UseCaseHelper;
import com.htc.util.Results;
import io.vavr.control.Either;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Реализация сценария получения всех задач.
 */
@Component
@AllArgsConstructor
public class GetAllTasks {

  private TasksRepository tasksRepository;

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
   * @return Список задач.
   */
  public CompletableFuture<Either<Failure, List<Task>>> execute(Set<String> permissions) {
    return UseCaseHelper.hasRolePermissions(permissions, permittedRoles)
            ? tasksRepository.getAll()
            : Results.fail(new Forbidden());
  }
}
