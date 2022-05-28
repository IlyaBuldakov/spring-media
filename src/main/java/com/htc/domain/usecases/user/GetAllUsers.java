package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Реализация сценария получения всех пользователей
 *
 * @author IlyaBuldakov
 */

@AllArgsConstructor
public final class GetAllUsers implements UseCase<Void, List<User>> {

    private final UserRepository userRepository;

    @Override
    public Future<Either<Failure, List<User>>> execute(Void param) {
        return userRepository.getAll();
    }
}
