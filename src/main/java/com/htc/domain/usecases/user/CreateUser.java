package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UsersRepository;
import com.htc.infrastructure.jpa.UsersRepositoryImpl;
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
     * Пример: {@link UsersRepositoryImpl}.
     */
    private UsersRepository usersRepository;

    /**
     * Метод сценария.
     *
     * @return Пользователь.
     */
    public CompletableFuture<Either<Failure, User>> execute(String name, String password,
                                                            String email, String avatar, Role role) {
        var expectedFailure = ValuesValidator.checkUserFields(
                params.getName(),
                params.getPassword(),
                params.getEmail(),
                params.getAvatar()
        );
        return expectedFailure == null ?
                usersRepository.create(
                        params.getName(),
                        params.getPassword(),
                        params.getEmail(),
                        params.getAvatar(),
                        params.getRole()) : CompletableFuture.completedFuture(Either.left(expectedFailure));
    }
}
