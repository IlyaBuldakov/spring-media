package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.Unauthorized;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UsersRepository;
import com.htc.domain.usecases.UseCaseHelper;
import com.htc.infrastructure.repositories.UsersRepositoryImpl;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * Реализация сценария получения пользователя по идентификатору.
 */
@AllArgsConstructor
@Component
public class GetUserById {

  /**
   * Поле для внедрения реализации из infrastructure layer.
   * Пример: {@link UsersRepositoryImpl}.
   */
  private UsersRepository usersRepository;

  /**
   * Роль, которой разрешено данное действие.
   */
  private final Role permittedRole = Role.ADMIN;

  /**
   * Метод сценария.
   *
   * @param param Идентификатор.
   * @return Список пользователей.
   */
  public CompletableFuture<Either<Failure, User>> execute(Set<String> permissions, String param) {
    var expectedFailure = ValuesValidator.validateStringId(param);
    if (expectedFailure != null) {
      return CompletableFuture.completedFuture(Either.left(expectedFailure));
    }
    return UseCaseHelper.hasRolePermissions(permissions, permittedRole)
            ? usersRepository.getById(Integer.parseInt(param))
            : CompletableFuture.completedFuture(Either.left(Unauthorized.FORBIDDEN));
  }
}
