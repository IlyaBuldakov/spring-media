package ru.kiryanovid.domain.usecases.user;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.repositories.UserRepositories;
import ru.kiryanovid.domain.usecases.UseCase;

import java.util.concurrent.CompletableFuture;

/**
 * Удаление пользователя по идентификатору
 */
@Component
@RequiredArgsConstructor
public final class DeleteUserById implements UseCase<Integer, Void> {
    @Autowired
    private final UserRepositories repositories;
    @Override
    public CompletableFuture<Either<Failure, Void>> execute(Integer id) {
        repositories.delete(id);
        return null;
    }
}
