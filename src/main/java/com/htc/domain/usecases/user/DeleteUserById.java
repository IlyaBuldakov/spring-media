package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UsersRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.infrastructure.jpa.UsersRepositoryImpl;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * Реализация сценария удаления пользователя по идентификатору.
 */
@AllArgsConstructor
@Component
public class DeleteUserById implements UseCase<String, User> {

    /**
     * Поле для внедрения реализации из infrastructure layer.
     * Пример: {@link UsersRepositoryImpl}.
     */
    private UsersRepository usersRepository;

    /**
     * Метод сценария.
     *
     * @param param Идентификатор.
     * @return Пользователь.
     */
    public CompletableFuture<Either<Failure, Void>> execute(String param) {
        var expectedFailure = ValuesValidator.validateStringId(param);
        return expectedFailure == null ? usersRepository.deleteById(Integer.parseInt(param))
                : CompletableFuture.completedFuture(Either.left(expectedFailure));
    }
}
