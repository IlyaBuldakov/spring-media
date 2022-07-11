package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValue;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UsersRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.infrastructure.jpa.UsersRepositoryImpl;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.apache.commons.validator.routines.IntegerValidator;
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
    @Override
    public CompletableFuture<Either<Failure, User>> execute(String param) {
        IntegerValidator integerValidator = IntegerValidator.getInstance();
        if (!integerValidator.isValid(param)) {
            return CompletableFuture.completedFuture(Either.left(new InvalidValue("Некорректное значение идентификатора")));
        }
        int paramToInt = Integer.parseInt(param);
        if (!integerValidator.minValue(paramToInt, 1)) {
            return CompletableFuture.completedFuture(Either.left(new InvalidValue("Идентификатор должен быть больше 0")));
        }
        return usersRepository.deleteById(paramToInt);
    }
}
