package com.htc.application.services.impl;

import com.htc.application.dto.errors.BadRequestResponse;
import com.htc.application.dto.errors.InternalServerErrorResponse;
import com.htc.application.dto.errors.NotFoundResponse;
import com.htc.application.dto.user.UserRequest;
import com.htc.application.dto.user.UserResponse;
import com.htc.application.services.UsersService;
import com.htc.domain.entities.failures.InvalidValuesContainer;
import com.htc.domain.entities.failures.NotFound;
import com.htc.util.UserParams;
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
                        either.map(UserResponse::new).getOrElseThrow(
                                failure -> switch (failure) {
                                    case InvalidValuesContainer invalidValues ->
                                            new BadRequestResponse(invalidValues);
                                    case NotFound notFound ->
                                            new NotFoundResponse(notFound);
                                    default -> new InternalServerErrorResponse();
                                }
                        ));
    }

    /**
     * Создание пользователя.
     *
     * @param userRequest Представление пользователя {@link UserRequest}.
     * @return Представление созданного пользователя {@link UserResponse}.
     */
    @Override
    public CompletableFuture<UserResponse> create(UserRequest userRequest) {
        return createUser.execute(new UserParams(userRequest))
                .thenApply(either ->
                        either.map(UserResponse::new).getOrElseThrow(
                                failure -> switch (failure) {
                                    case InvalidValuesContainer invalidValues ->
                                            new BadRequestResponse(invalidValues);
                                    default -> new InternalServerErrorResponse();
                                }
                        ));
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
        return updateUser.execute(new UserParams(id, userRequest))
                .thenApply(either ->
                        either.map(UserResponse::new).getOrElseThrow(
                                failure -> switch (failure) {
                                    case InvalidValuesContainer invalidValues ->
                                            new BadRequestResponse(invalidValues);
                                    case NotFound notFound ->
                                            new NotFoundResponse(notFound);
                                    default -> new InternalServerErrorResponse();
                                }
                        ));
    }


    /**
     * Удаление пользователя.
     *
     * @param id Идентификатор пользователя.
     * @return Представление удалённого пользователя {@link UserResponse}.
     */
    @Override
    public void delete(String id) {
        deleteUserById.execute(id);
    }
}
