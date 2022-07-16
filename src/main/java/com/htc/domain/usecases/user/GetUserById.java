package com.htc.domain.usecases.user;

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
     * Метод сценария.
     *
     * @param param Идентификатор.
     * @return Список пользователей.
     */
    public CompletableFuture<Either<Failure, User>> execute(String param) {
        var expectedFailure = ValuesValidator.validateStringId(param);
        return expectedFailure == null ? usersRepository.getById(Integer.parseInt(param))
                : CompletableFuture.completedFuture(Either.left(expectedFailure));
    }
}
