package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;

import java.util.concurrent.Future;

/**
 * Реализация сценария обновления пользователя
 *
 * @author IlyaBuldakov
 */

@AllArgsConstructor
public final class UpdateUser implements UseCase<User, User> {

    private final UserRepository userRepository;

    @Override
    public Future<Either<Failure, User>> execute(User param) {
        return userRepository.update(param);
    }
}
