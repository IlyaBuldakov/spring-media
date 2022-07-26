package com.htc.application.controllers;

import com.htc.application.dto.user.UserRequest;
import com.htc.application.dto.user.UserResponse;
import com.htc.domain.usecases.UserUseCase;
import com.htc.utility.Controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import java.util.Collection;
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
@AllArgsConstructor
@SecurityRequirement(name = "JWT")
@Tags(@Tag(name = "Пользователи"))
public class UserController {
  private UserUseCase.Create create;
  private UserUseCase.UpdateUser updateUser;
  private UserUseCase.DeleteUserById deleteUserById;
  private UserUseCase.GetUserById getUserById;
  private UserUseCase.GetAllUsers getAllUsers;

  /**
   * Создаёт пользователя.
   */
  @PostMapping
  @Async
  @Operation(summary = "Создать нового пользователя")
  public void create(@RequestBody UserRequest userRequest) {
    Controllers.handleRequest(
            create,
            new UserUseCase.Create.Params(
                    userRequest.name(),
                    userRequest.email(),
                    userRequest.password(),
                    userRequest.image(),
                    userRequest.role()),
            null);
  }

  /**
   * Возвращает пользователя.
   *
   * @param id Идентификатор пользователя.
   * @return Пользователь.
   */
  @GetMapping(path = "/{id}")
  @Async
  @Operation(summary = "Получить пользователя по идентификатору")
  public CompletableFuture<UserResponse> get(@PathVariable Integer id) {
    return Controllers.handleRequest(
            getUserById,
            id,
            UserResponse::new);
  }

  /**
   * Возвращает список всех пользователей.
   *
   * @return Список пользователей.
   */
  @GetMapping
  @Async
  @Operation(summary = "Получить список пользователей")
  public CompletableFuture<Collection<UserResponse>> getAll() {
    return Controllers.handleRequest(
            getAllUsers,
            null,
            users -> users.stream()
                    .map(UserResponse::new)
                    .collect(Collectors.toList()));
  }

  /**
   * Обновляет данные пользователя.
   *
   * @param id Идентификатор пользователя.
   * @param userRequest Представление сущности пользователя.
   */
  @PutMapping(path = "/{id}")
  @Async
  @Operation(summary = "Изменить пользователя по идентификатору")
  public void update(@PathVariable int id, @RequestBody UserRequest userRequest) {
    Controllers.handleRequest(
            updateUser,
            new UserUseCase.UpdateUser.Params(
                    id,
                    userRequest.name(),
                    userRequest.email(),
                    userRequest.password(),
                    userRequest.image(),
                    userRequest.role()),
            null);
  }

  /**
   * Удаляет пользователя.
   *
   * @param id Идентификатор пользователя.
   */
  @DeleteMapping(path = "/{id}")
  @Async
  @Operation(summary = "Удалить пользователя по идентификатору")
  public void delete(@PathVariable int id) {
    Controllers.handleRequest(
            deleteUserById,
            id,
            null);
  }
}
