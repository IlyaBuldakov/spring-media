package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UsersRepository;
import com.htc.infrastructure.repositories.UsersRepositoryImpl;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

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
     * Метод сценария.
     *
     * @return Пользователь.
     */
    public CompletableFuture<Either<Failure, User>> execute(String id, String name, String password,
                                                            String email, String avatar, Role role) {
        var expectedFailure
                = ValuesValidator.checkUserFields(id, name, password, email, avatar);
        return expectedFailure == null ?
                usersRepository.update(Integer.parseInt(id), name, password, email, avatar, role)
                : CompletableFuture.completedFuture(Either.left(expectedFailure));
    }
}
