package com.htc.application.services.impl;

import com.htc.application.dto.user.UserRequest;
import com.htc.application.dto.user.UserResponse;
import com.htc.application.services.UsersService;
import com.htc.domain.entities.UserParams;
import com.htc.domain.usecases.user.CreateUser;
import com.htc.domain.usecases.user.DeleteUserById;
import com.htc.domain.usecases.user.GetAllUsers;
import com.htc.domain.usecases.user.GetUserById;
import com.htc.domain.usecases.user.UpdateUser;
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
    public CompletableFuture<List<UserResponse>> getAll() {
        return getAllUsers.execute(null)
                .thenApply(either -> either
                        .map(list -> list.parallelStream()
                                .map(UserResponse::new)).get().toList());
    }

    @Override
    public CompletableFuture<UserResponse> getById(String id) {
        return getUserById.execute(id)
                .thenApply(either -> either
                        .map(UserResponse::new).get());
    }

    @Override
    public CompletableFuture<UserResponse> create(UserRequest userRequest) {
        return createUser.execute(new UserParams(userRequest))
                .thenApply(either -> either
                        .map(UserResponse::new).get());
    }

    @Override
    public CompletableFuture<UserResponse> update(UserRequest userRequest, String id) {
        return updateUser.execute(new UserParams(id, userRequest))
                .thenApply(either -> either
                        .map(UserResponse::new).get());
    }

    @Override
    public CompletableFuture<UserResponse> delete(String id) {
        return deleteUserById.execute(id)
                .thenApply(either
                        -> either.map(UserResponse::new).get());
    }
}
