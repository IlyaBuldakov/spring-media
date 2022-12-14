package com.htc.domain.usecases.user;

import com.htc.domain.entities.failure.AlreadyExists;
import com.htc.domain.entities.failure.Failure;
import com.htc.domain.entities.failure.Forbidden;
import com.htc.domain.entities.user.Role;
import com.htc.domain.repositories.UsersRepository;
import com.htc.domain.usecases.UseCaseHelper;
import com.htc.util.Results;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Component;

/**
 * Реализация сценария создания пользователя.
 */
@Component
public class CreateUser {

  public CreateUser(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  private final UsersRepository usersRepository;

  /**
   * Роль, которой разрешено данное действие.
   */
  private final Role permittedRole = Role.ADMIN;

  /**
   * Метод сценария.
   *
   * @return Пользователь.
   */
  public CompletableFuture<Either<Failure, Void>> execute(Set<String> permissions, String name,
                                                          String password, String email,
                                                          String avatar, Role role) {
    var expectedFailure = ValuesValidator.checkUserFields(name, password, email, avatar);
    if (expectedFailure != null) {
      return Results.fail(expectedFailure);
    }
    if (usersRepository.userExistsByEmail(email)) {
      return Results.fail(AlreadyExists.USER);
    }
    return UseCaseHelper.hasRolePermissions(permissions, permittedRole)
            ? usersRepository.create(name, password, email, avatar, role)
            : Results.fail(new Forbidden());
  }
}
