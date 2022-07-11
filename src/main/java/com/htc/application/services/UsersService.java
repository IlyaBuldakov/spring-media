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

    CompletableFuture<List<UserResponse>> getAll();
    CompletableFuture<UserResponse> getById(String id);
    CompletableFuture<UserResponse> create(UserRequest user);
    CompletableFuture<UserResponse> update(UserRequest user, String id);
    CompletableFuture<UserResponse> delete(String id);
}
