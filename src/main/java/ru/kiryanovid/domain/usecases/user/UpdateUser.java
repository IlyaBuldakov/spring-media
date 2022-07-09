package ru.kiryanovid.domain.usecases.user;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.entity.users.User;
import ru.kiryanovid.domain.repositories.UserRepositories;
import ru.kiryanovid.domain.usecases.UseCase;

import java.util.concurrent.CompletableFuture;

/**
 * Обновление пользователя
 */
@Component
@RequiredArgsConstructor
public final class UpdateUser implements UseCase<User, User> {
    @Autowired
    UserRepositories repositories;
    @Override
    public CompletableFuture<Either<Failure, User>> execute(User user) {
        repositories.update(user);
        return null;
    }
}
