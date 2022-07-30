package com.htc.domain.usecases.task;

import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.failure.Failure;
import com.htc.domain.entities.failure.Unauthorized;
import com.htc.domain.entities.user.Role;
import com.htc.domain.repositories.TasksRepository;
import com.htc.domain.repositories.UsersRepository;
import com.htc.domain.usecases.UseCaseHelper;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import java.time.LocalDate;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Реализация сценария создания задачи.
 */
@AllArgsConstructor
@Component
public class CreateTask {

  TasksRepository tasksRepository;

  UsersRepository usersRepository;

  /**
   * Роли, которым разрешено данное действие.
   */
  private final Role[] permittedRoles = new Role[]{
          Role.ADMIN,
          Role.MANAGER
  };

  /**
   * Метод сценария.
   *
   * @param name        Имя задачи.
   * @param type        Тип контента задачи.
   * @param description Описание задачи.
   * @param author      Автор задачи.
   * @param executor    Исполнитель задачи.
   * @param dateExpired Дата выполнения задачи (срок выполнения).
   * @return void.
   */
  public CompletableFuture<Either<Failure, Void>> execute(Set<String> permissions,
                                                          String name, ContentType type,
                                                          String description, String author,
                                                          String executor, LocalDate dateExpired) {
    var expectedFailure
            = ValuesValidator.checkTaskFields(name, description, author, executor);
    if (expectedFailure != null) {
      return CompletableFuture.completedFuture(Either.left(expectedFailure));
    }
    return UseCaseHelper.hasRolePermissions(permissions, permittedRoles)
            ? tasksRepository.create(name, type, description,
            Integer.parseInt(author), Integer.parseInt(executor), dateExpired)
            : CompletableFuture.completedFuture(Either.left(Unauthorized.FORBIDDEN));
  }
}
