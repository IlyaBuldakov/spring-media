package com.htc.domain.usecases.user;

import com.htc.util.UserParams;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UsersRepository;
import com.htc.infrastructure.jpa.UsersRepositoryImpl;
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
     * @param params Данные пользователя {@link UserParams}.
     * @return Пользователь.
     */
    @Override
    public CompletableFuture<Either<Failure, User>> execute(UserParams params) {
        var expectedFailure = ValuesValidator.checkUserFields(
                params.getId(),
                params.getName(),
                params.getPassword(),
                params.getEmail(),
                params.getAvatar()
        );
        return expectedFailure == null ?
                usersRepository.update(
                        Integer.parseInt(params.getId()),
                        params.getName(),
                        params.getPassword(),
                        params.getEmail(),
                        params.getAvatar(),
                        params.getRole()) : CompletableFuture.completedFuture(Either.left(expectedFailure));
    }
}
