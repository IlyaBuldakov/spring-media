package com.htc.domain.usecases.user;

import com.htc.util.UserParams;
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
 * Реализация сценария обновления пользователя.
 */
@AllArgsConstructor
@Component
public class UpdateUser implements UseCase<UserParams, User> {

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
        String notValidatedId = params.getId();

        IntegerValidator integerValidator = IntegerValidator.getInstance();
        if (!integerValidator.isValid(notValidatedId)) {
            return CompletableFuture.completedFuture(Either.left(InvalidValue.INCORRECT_ID));
        }
        int id = Integer.parseInt(notValidatedId);
        if (!integerValidator.minValue(id, 1)) {
            return CompletableFuture.completedFuture(Either.left(InvalidValue.NEGATIVE_ID));
        }
        return usersRepository.update(
                id,
                params.getName(),
                params.getPassword(),
                params.getEmail(),
                params.getAvatar(),
                params.getRole());
    }
}
