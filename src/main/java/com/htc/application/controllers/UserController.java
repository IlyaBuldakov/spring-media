package com.htc.application.controllers;

import com.htc.application.dto.user.UserRequest;
import com.htc.application.dto.user.UserResponse;
import com.htc.domain.usecases.user.CreateUser;
import com.htc.domain.usecases.user.DeleteUserById;
import com.htc.domain.usecases.user.GetAllUsers;
import com.htc.domain.usecases.user.GetUserById;
import com.htc.domain.usecases.user.UpdateUserById;
import com.htc.utility.Controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с пользователями.
 */
@RestController
@RequestMapping(path = "api/users")
@SecurityRequirement(name = "JWT")
@AllArgsConstructor
@Tags(@Tag(name = "Пользователи"))
public class UserController {
  private CreateUser createUser;
  private UpdateUserById updateUserById;
  private DeleteUserById deleteUserById;
  private GetUserById getUserById;
  private GetAllUsers getAllUsers;

  /**
   * Создаёт нового пользователя.
   *
   * @param userRequest Представление сущности пользователя.
   */
  @PostMapping
  @Operation(summary = "Создать нового пользователя.")
  @Async
  public void create(@RequestBody UserRequest userRequest) {
    Controllers.handleRequest(
        createUser,
        new CreateUser.Params(
            userRequest.name, "name",
            userRequest.email, "email",
            userRequest.password, "password",
            userRequest.image, "image",
            userRequest.role, "role"),
        null);
  }

  /**
   * Изменяет пользователя по идентификатору.
   *
   * @param id Идентификатор пользователя.
   * @param userRequest Представление сущности пользователя.
   */
  @PutMapping(path = "/{id}")
  @Operation(summary = "Изменить пользователя по идентификатору.")
  @Async
  public void update(@PathVariable int id, @RequestBody UserRequest userRequest) {
    Controllers.handleRequest(
        updateUserById,
        new UpdateUserById.Params(
            id, "id",
            userRequest.name, "name",
            userRequest.email, "email",
            userRequest.password, "password",
            userRequest.image, "image",
            userRequest.role, "role"),
        null);
  }

  /**
   * Удаляет пользователя по идентификатору.
   *
   * @param id Идентификатор пользователя.
   */
  @DeleteMapping(path = "/{id}")
  @Operation(summary = "Удалить пользователя по идентификатору.")
  @Async
  public void delete(@PathVariable int id) {
    Controllers.handleRequest(
        deleteUserById,
        new DeleteUserById.Params(id, "id"),
        null);
  }

  /**
   * Получает пользователя по идентификатору.
   *
   * @param id Идентификатор пользователя.
   * @return Представление сущности пользователя.
   */
  @GetMapping(path = "/{id}")
  @Operation(summary = "Получить пользователя по идентификатору.")
  @Async
  public CompletableFuture<UserResponse> get(@PathVariable int id) {
    return Controllers.handleRequest(
        getUserById,
        new GetUserById.Params(id, "id"),
        UserResponse::new);
  }

  /**
   * Получает список пользователей.
   *
   * @return Список представлений сущности пользователя.
   */
  @GetMapping
  @Operation(summary = "Получить список пользователей.")
  @Async
  public CompletableFuture<Iterable<UserResponse>> getAll() {
    return Controllers.handleRequest(
        getAllUsers,
        null,
        users -> users.stream()
            .map(UserResponse::new)
            .collect(Collectors.toList()));
  }
}
