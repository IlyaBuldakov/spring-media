package com.htc.application.services;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import io.vavr.control.Either;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Интерфейс, описывающий базовые операции для взаимодействия с пользователем
 *
 * @author IlyaBuldakov
 */
public interface UsersService {

    CompletableFuture<Either<Failure, List<User>>> getAll();
    CompletableFuture<Either<Failure, User>> getById(int id);
    CompletableFuture<Either<Failure, User>> create(User user);
    CompletableFuture<Either<Failure, User>> update(User user);
    CompletableFuture<Either<Failure, Void>> delete(int id);
}
