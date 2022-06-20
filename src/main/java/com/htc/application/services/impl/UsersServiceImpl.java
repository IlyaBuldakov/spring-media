package com.htc.application.services.impl;

import com.htc.application.dto.user.UserRequest;
import com.htc.application.dto.user.UserResponse;
import com.htc.application.services.UsersService;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.usecases.user.CreateUser;
import com.htc.domain.usecases.user.DeleteUserById;
import com.htc.domain.usecases.user.GetAllUsers;
import com.htc.domain.usecases.user.GetUserById;
import com.htc.domain.usecases.user.UpdateUser;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Реализация UsersService
 *
 * @author IlyaBuldakov
 */
@AllArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

    CreateUser createUser;
    GetUserById getUserById;
    GetAllUsers getAllUsers;
    UpdateUser updateUser;
    DeleteUserById deleteUserById;

    @Override
    public CompletableFuture<Either<Failure, List<UserResponse>>> getAll() {
        return getAllUsers.execute(null)
                .thenApply(either -> either
                        .map(list -> list.parallelStream()
                                .map(UserResponse::new)
                                .toList()));
    }

    @Override
    public CompletableFuture<Either<Failure, UserResponse>> getById(String id) {
        return null;
    }

    @Override
    public CompletableFuture<Either<Failure, UserResponse>> create(UserRequest user) {
        return null;
    }

    @Override
    public CompletableFuture<Either<Failure, UserResponse>> update(UserRequest user) {
        return null;
    }

    @Override
    public CompletableFuture<Either<Failure, Void>> delete(String id) {
        return null;
    }
}