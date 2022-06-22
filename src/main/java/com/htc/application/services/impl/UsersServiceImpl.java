package com.htc.application.services.impl;

import com.htc.application.dto.user.UserRequest;
import com.htc.application.dto.user.UserResponse;
import com.htc.application.services.UsersService;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.usecases.user.*;
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
    SearchUsers searchUsers;

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
        return getUserById.execute(id)
                .thenApply(either -> either
                        .map(UserResponse::new));
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

    @Override
    public CompletableFuture<Either<Failure, Void>> search(String query) {
        return null;
    }

}
