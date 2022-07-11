package com.htc.domain.usecases.user;

import com.htc.domain.entities.UserParams;
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
public class CreateUser implements UseCase<UserParams, User> {

    private UsersRepository usersRepository;

    @Override
    public CompletableFuture<Either<Failure, User>> execute(UserParams params) {
        return usersRepository.create(
                params.getName(),
                params.getPassword(),
                params.getEmail(),
                params.getAvatar(),
                params.getRole());
    }
}
