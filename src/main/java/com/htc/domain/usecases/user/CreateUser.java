package com.htc.domain.usecases.user;

import com.htc.util.UserParams;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UsersRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.infrastructure.jpa.UsersRepositoryImpl;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * Реализация сценария создания пользователя.
 */
@AllArgsConstructor
@Component
public class CreateUser implements UseCase<UserParams, User> {

    /**
     * Поле для внедрения реализации из infrastructure layer.
     * Пример: {@link UsersRepositoryImpl}.
     */
    private UsersRepository usersRepository;

    /**
     * Метод сценария.
     *
     * @param params Данные пользователя {@link UserParams}
     * @return Пользователь.
     */
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
