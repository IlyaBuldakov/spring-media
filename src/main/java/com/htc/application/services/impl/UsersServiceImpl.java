package com.htc.application.services.impl;

import com.htc.application.dto.user.UserRequest;
import com.htc.application.dto.user.UserResponse;
import com.htc.application.services.ExceptionDtoResolver;
import com.htc.application.services.UsersService;
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
 * Реализация {@link UsersService}.
 */
@AllArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

    CreateUser createUser;
    GetUserById getUserById;
    GetAllUsers getAllUsers;
    UpdateUser updateUser;
    DeleteUserById deleteUserById;

    /**
     * Получение списка пользователей.
     *
     * @return Список {@link UserResponse}.
     */
    @Override
    public CompletableFuture<List<UserResponse>> getAll() {
        return getAllUsers.execute()
                .thenApply(either -> either
                        .map(list -> list.parallelStream()
                                .map(UserResponse::new)).get().toList());
    }

    /**
     * Получение пользователя по идентификатору.
     *
     * @param id Идентификатор пользователя.
     * @return Представление пользователя {@link UserResponse}.
     */
    @Override
    public CompletableFuture<UserResponse> getById(String id) {
        return getUserById.execute(id)
                .thenApply(either ->
                        either.map(UserResponse::new)
                                .getOrElseThrow(ExceptionDtoResolver::resolve));
    }

    /**
     * Создание пользователя.
     *
     * @param userRequest Представление пользователя {@link UserRequest}.
     * @return Представление созданного пользователя {@link UserResponse}.
     */
    @Override
    public CompletableFuture<UserResponse> create(UserRequest userRequest) {
        return createUser.execute(
                        userRequest.getName(), userRequest.getPassword(),
                        userRequest.getEmail(), userRequest.getAvatar(), userRequest.getRole()
                )
                .thenApply(either ->
                        either.map(UserResponse::new)
                                .getOrElseThrow(ExceptionDtoResolver::resolve));
    }

    /**
     * Обновление пользователя.
     *
     * @param userRequest Представление пользователя {@link UserRequest}
     *                    (данные для обновления).
     * @param id          Идентификатор пользователя.
     * @return Представление обновлённого пользователя {@link UserResponse}.
     */
    @Override
    public CompletableFuture<UserResponse> update(UserRequest userRequest, String id) {
        return updateUser.execute(
                        id, userRequest.getName(), userRequest.getPassword(),
                        userRequest.getEmail(), userRequest.getAvatar(), userRequest.getRole())
                .thenApply(either ->
                        either.map(UserResponse::new)
                                .getOrElseThrow(ExceptionDtoResolver::resolve));
    }


    /**
     * Удаление пользователя.
     *
     * @param id Идентификатор пользователя.
     * @return Представление удалённого пользователя {@link UserResponse}.
     */
    @Override
    public CompletableFuture<Void> delete(String id) {
        return deleteUserById.execute(id)
                .thenApply(either -> {
                    if (either.isLeft()) {
                        throw ExceptionDtoResolver.resolve(either.getLeft());
                    }
                    return null;
                });
    }
}
