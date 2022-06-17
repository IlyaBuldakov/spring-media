package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UsersRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;

import java.util.concurrent.Future;

/**
 * Реализация сценария создания пользователя
 *
 * @author IlyaBuldakov
 */

@AllArgsConstructor
public final class CreateUser implements UseCase<User, User> {

    private final UsersRepository usersRepository;

    @Override
    public Future<Either<Failure, User>> execute(User param) {
        return userRepository.create(param);
    }
}
