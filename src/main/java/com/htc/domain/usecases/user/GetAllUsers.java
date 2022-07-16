package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UsersRepository;
import com.htc.infrastructure.jpa.UsersRepositoryImpl;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Реализация сценария получения всех пользователей.
 */
@AllArgsConstructor
@Component
public class GetAllUsers {

    /**
     * Поле для внедрения реализации из infrastructure layer.
     * Пример: {@link UsersRepositoryImpl}.
     */
    private UsersRepository usersRepository;

    /**
     * Метод сценария.
     *
     * @return Список пользователей.
     */
    public CompletableFuture<Either<Failure, List<User>>> execute() {
        return usersRepository.getAll();
    }
}
