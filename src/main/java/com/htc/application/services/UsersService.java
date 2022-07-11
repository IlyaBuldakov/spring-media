package com.htc.application.services;

import com.htc.application.dto.user.UserRequest;
import com.htc.application.dto.user.UserResponse;
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

    CompletableFuture<List<UserResponse>> getAll();
    CompletableFuture<UserResponse> getById(String id);
    CompletableFuture<UserResponse> create(UserRequest user);
    CompletableFuture<UserResponse> update(UserRequest user, String id);
    CompletableFuture<UserResponse> delete(String id);
}
