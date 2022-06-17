package com.htc.application.services;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import io.vavr.control.Either;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Интерфейс, описывающий базовые операции для взаимодействия с пользователем
 *
 * @author IlyaBuldakov
 */
public interface UsersService {

    CompletableFuture<Either<Failure, User>> getAll();
    CompletableFuture<Either<Failure, User>> getById();
    CompletableFuture<Either<Failure, User>> create();
    CompletableFuture<Either<Failure, User>> update();
    CompletableFuture<Either<Failure, User>> delete();
}
