package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UsersRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Реализация сценария получения всех пользователей
 *
 * @author IlyaBuldakov
 */
@AllArgsConstructor
@Component
public class GetAllUsers implements UseCase<Void, List<User>> {

    private UsersRepository usersRepository;

    @Override
    public CompletableFuture<Either<Failure, List<User>>> execute(Void param) {
        return usersRepository.getAll();
    }
}
