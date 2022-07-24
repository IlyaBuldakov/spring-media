package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.Unauthorized;
import com.htc.domain.entities.user.Role;
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
 * Реализация сценария обновления пользователя.
 */
@AllArgsConstructor
@Component
public class UpdateUser {

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
     * @return Пользователь.
     */
    public CompletableFuture<Either<Failure, Void>> execute(Set<String> permissions, String id, String name, String password,
                                                            String email, String avatar, Role role) {
        var expectedFailure
                = ValuesValidator.checkUserFields(id, name, password, email, avatar);
        if (expectedFailure != null) {
            return CompletableFuture.completedFuture(Either.left(expectedFailure));
        }
        return UseCaseHelper.hasRolePermissions(permissions, permittedRole)
                ? usersRepository.update(Integer.parseInt(id), name, password, email, avatar, role)
                : CompletableFuture.completedFuture(Either.left(Unauthorized.FORBIDDEN));
    }
}
