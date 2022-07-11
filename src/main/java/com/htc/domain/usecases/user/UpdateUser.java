package com.htc.domain.usecases.user;

import com.htc.domain.entities.UserParams;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValue;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UsersRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.apache.commons.validator.routines.IntegerValidator;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * Реализация сценария обновления пользователя
 *
 * @author IlyaBuldakov
 */
@AllArgsConstructor
@Component
public class UpdateUser implements UseCase<UserParams, User> {

    private UsersRepository usersRepository;

    @Override
    public CompletableFuture<Either<Failure, User>> execute(UserParams params) {
        String notValidatedId = params.getId();

        IntegerValidator integerValidator = IntegerValidator.getInstance();
        if (!integerValidator.isValid(notValidatedId)) {
            return CompletableFuture.completedFuture(Either.left(new InvalidValue("Некорректное значение идентификатора")));
        }
        int id = Integer.parseInt(notValidatedId);
        if (!integerValidator.minValue(id, 1)) {
            return CompletableFuture.completedFuture(Either.left(new InvalidValue("Идентификатор должен быть больше 0")));
        }
        return usersRepository.update(
                id,
                params.getName(),
                params.getPassword(),
                params.getEmail(),
                params.getAvatar(),
                params.getRole());
    }

    public Void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        return null;
    }
}
