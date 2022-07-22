package com.htc.application.services;

import com.htc.application.dto.user.UserRequest;
import com.htc.application.dto.user.UserResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Интерфейс, описывающий базовые операции для взаимодействия с пользователем.
 * Слой преобразования DTO <---> Domain entity.
 */
public interface UsersService {

    /**
     * Получение списка пользователей.
     *
     * @return Список пользователей.
     */
    CompletableFuture<List<UserResponse>> getAll();

    /**
     * Получение пользователя по идентификатору.
     *
     * @param id Идентификатор пользователя.
     * @return {@link UserResponse Представление} пользователя.
     */
    CompletableFuture<UserResponse> getById(String id);

    /**
     * Создание пользователя.
     *
     * @param user {@link UserRequest Представление} пользователя (запрос).
     * @return void.
     */
    CompletableFuture<Void> create(UserRequest user);

    /**
     * Обновление пользователя.
     *
     * @param user {@link UserRequest Представление} пользователя (запрос).
     * @param id Идентификатор пользователя.
     * @return void.
     */
    CompletableFuture<Void> update(UserRequest user, String id);

    /**
     * Удаление пользователя по идентификатору.
     *
     * @param id Идентификатор пользователя.
     * @return void.
     */
    CompletableFuture<Void> delete(String id);
}
