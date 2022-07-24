package com.htc.application.services.impl;

import com.htc.application.dto.user.UserRequest;
import com.htc.application.dto.user.UserResponse;
import com.htc.application.services.ExceptionDtoResolver;
import com.htc.application.services.ServiceHelper;
import com.htc.application.services.UsersService;
import com.htc.domain.usecases.user.CreateUser;
import com.htc.domain.usecases.user.DeleteUserById;
import com.htc.domain.usecases.user.GetAllUsers;
import com.htc.domain.usecases.user.GetUserByEmail;
import com.htc.domain.usecases.user.GetUserById;
import com.htc.domain.usecases.user.UpdateUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
    GetUserByEmail getUserByEmail;
    UpdateUser updateUser;
    DeleteUserById deleteUserById;

    /**
     * Получение списка пользователей.
     *
     * @return Список {@link UserResponse}.
     */
    @Override
    public CompletableFuture<List<UserResponse>> getAll(Collection<? extends GrantedAuthority> authorities) {
        var permissions = ServiceHelper.getPermissions(authorities);
        return getAllUsers.execute(permissions)
                .thenApply(either -> either
                        .map(list -> list.parallelStream()
                                .map(UserResponse::new)).getOrElseThrow(
                                        ExceptionDtoResolver::resolve).toList());
    }

    /**
     * Получение пользователя по идентификатору.
     *
     * @param id Идентификатор пользователя.
     * @return Представление пользователя {@link UserResponse}.
     */
    @Override
    public CompletableFuture<UserResponse> getById(Collection<? extends GrantedAuthority> authorities, String id) {
        var permissions = ServiceHelper.getPermissions(authorities);
        return getUserById.execute(permissions, id)
                .thenApply(either ->
                        either.map(UserResponse::new)
                                .getOrElseThrow(ExceptionDtoResolver::resolve));
    }

    @Override
    public CompletableFuture<UserResponse> getByEmail(String email) {
        return getUserByEmail.execute(email)
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
    public CompletableFuture<Void> create(Collection<? extends GrantedAuthority> authorities, UserRequest userRequest) {
        var permissions = ServiceHelper.getPermissions(authorities);
        return createUser.execute(
                        permissions, userRequest.getName(), userRequest.getPassword(),
                        userRequest.getEmail(), userRequest.getAvatar(), userRequest.getRole())
                .thenApply(either -> {
                    if (either.isLeft()) {
                        throw ExceptionDtoResolver.resolve(either.getLeft());
                    }
                    return null;
                });
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
    public CompletableFuture<Void> update(Collection<? extends GrantedAuthority> authorities, UserRequest userRequest, String id) {
        var permissions = ServiceHelper.getPermissions(authorities);
        return updateUser.execute(
                        permissions, id, userRequest.getName(), userRequest.getPassword(),
                        userRequest.getEmail(), userRequest.getAvatar(), userRequest.getRole())
                .thenApply(either -> {
                    if (either.isLeft()) {
                        throw ExceptionDtoResolver.resolve(either.getLeft());
                    }
                    return null;
                });
    }


    /**
     * Удаление пользователя.
     *
     * @param id Идентификатор пользователя.
     * @return Представление удалённого пользователя {@link UserResponse}.
     */
    @Override
    public CompletableFuture<Void> delete(Collection<? extends GrantedAuthority> authorities, String id) {
        var permissions = ServiceHelper.getPermissions(authorities);
        return deleteUserById.execute(permissions, id)
                .thenApply(either -> {
                    if (either.isLeft()) {
                        throw ExceptionDtoResolver.resolve(either.getLeft());
                    }
                    return null;
                });
    }
}
