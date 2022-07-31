package com.htc.domain.usecases.user;

import com.htc.domain.entities.failure.Failure;
import com.htc.domain.entities.failure.Forbidden;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UsersRepository;
import com.htc.domain.usecases.UseCaseHelper;
import com.htc.util.Results;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Реализация сценария получения пользователя по идентификатору.
 */
@AllArgsConstructor
@Component
public class GetUserById {

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
      return Results.fail(expectedFailure);
    }
    return UseCaseHelper.hasRolePermissions(permissions, permittedRole)
            ? usersRepository.getById(Integer.parseInt(param))
            : Results.fail(new Forbidden());
  }
}
