package com.htc.application.controllers;

import com.htc.application.dto.user.UserRequest;
import com.htc.application.dto.user.UserResponse;
import com.htc.domain.usecases.user.CreateUser;
import com.htc.domain.usecases.user.DeleteUserById;
import com.htc.domain.usecases.user.GetAllUsers;
import com.htc.domain.usecases.user.GetUserById;
import com.htc.domain.usecases.user.UpdateUser;
import com.htc.utility.Controllers;
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
public class UserController {
  private CreateUser createUser;
  private UpdateUser updateUser;
  private DeleteUserById deleteUserById;
  private GetUserById getUserById;
  private GetAllUsers getAllUsers;

  /**
   * Создаёт пользователя.
   */
  @PostMapping
  @Async
  public void create(@RequestBody UserRequest userRequest) {
    Controllers.handleRequest(
            createUser,
            new CreateUser.Params(
                    userRequest.getName(),
                    userRequest.getEmail(),
                    userRequest.getPassword(),
                    userRequest.getImage(),
                    userRequest.getRole()),
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
  public void update(@PathVariable int id, @RequestBody UserRequest userRequest) {
    Controllers.handleRequest(
            updateUser,
            new UpdateUser.Params(
                    id,
                    userRequest.getName(),
                    userRequest.getEmail(),
                    userRequest.getPassword(),
                    userRequest.getImage(),
                    userRequest.getRole()),
            null);
  }

  /**
   * Удаляет пользователя.
   *
   * @param id Идентификатор пользователя.
   */
  @DeleteMapping(path = "/{id}")
  @Async
  public void delete(@PathVariable int id) {
    Controllers.handleRequest(
            deleteUserById,
            id,
            null);
  }
}
