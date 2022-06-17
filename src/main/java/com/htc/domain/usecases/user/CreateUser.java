package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UsersRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * Реализация сценария создания пользователя
 *
 * @author IlyaBuldakov
 */
@AllArgsConstructor
@Component
public final class CreateUser implements UseCase<User, User> {

    private final UsersRepository usersRepository;

    @Override
    public CompletableFuture<Either<Failure, User>> execute(User param) {
        return usersRepository.create(param);
    }
}
