package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.Role;
import com.htc.domain.repositories.UsersRepository;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * Реализация сценария создания пользователя.
 */
@AllArgsConstructor
@Component
public class CreateUser {

    /**
     * Поле для внедрения реализации из infrastructure layer.
     */
    private UsersRepository usersRepository;

    /**
     * Метод сценария.
     *
     * @return Пользователь.
     */
    public CompletableFuture<Either<Failure, Void>> execute(String name, String password,
                                                            String email, String avatar, Role role) {
        var expectedFailure = ValuesValidator.checkUserFields(
                name, password, email, avatar
        );
        return expectedFailure == null ?
                usersRepository.create(
                       name, password, email, avatar, role)
                : CompletableFuture.completedFuture(Either.left(expectedFailure));
    }
}
