package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;

import java.util.concurrent.Future;

/**
 * Реализация сценария удаления пользователя по идентификатору
 *
 * @author IlyaBuldakov
 */

@AllArgsConstructor
public final class DeleteUserById implements UseCase<Integer, Void> {

    private final UserRepository userRepository;

    @Override
    public Future<Either<Failure, Void>> execute(Integer param) {
        return userRepository.delete(param);
    }
}
