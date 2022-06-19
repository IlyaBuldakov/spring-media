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
 * Реализация сценария обновления пользователя
 *
 * @author IlyaBuldakov
 */
@AllArgsConstructor
@Component
public class UpdateUser implements UseCase<User, User> {

    private UsersRepository usersRepository;

    @Override
    public CompletableFuture<Either<Failure, User>> execute(User param) {
        return usersRepository.update(param);
    }

    public Void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        return null;
    }
}
