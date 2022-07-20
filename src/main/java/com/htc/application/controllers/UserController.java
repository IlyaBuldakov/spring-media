package com.htc.application.controllers;

import com.htc.application.dtos.user.UserRequest;
import com.htc.application.dtos.user.UserResponse;
import com.htc.domain.usecases.user.AddUser;
import com.htc.domain.usecases.user.DeleteUserById;
import com.htc.domain.usecases.user.GetAllUsers;
import com.htc.domain.usecases.user.GetUserById;
import com.htc.domain.usecases.user.UpdateUserById;
import com.htc.utility.ControllerHelper;
import io.swagger.v3.oas.annotations.Operation;
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
 * Контроллер пользователя.
 */
@RestController
@RequestMapping(path = "api/users")
@AllArgsConstructor
public class UserController {
  private AddUser addUser;
  private UpdateUserById updateUserById;
  private DeleteUserById deleteUserById;
  private GetUserById getUserById;
  private GetAllUsers getAllUsers;

  /**
   * Добавление пользователя.
   */
  @PostMapping
  @Operation(summary = "Добавить нового пользователя.")
  @Async
  public void add(@RequestBody UserRequest userRequest) {
    ControllerHelper.customRequest(
            addUser,
            new AddUser.Params(
                    userRequest.getName(), "name",
                    userRequest.getEmail(), "email",
                    userRequest.getPassword(), "password",
                    userRequest.getAvatar(), "image",
                    userRequest.getRole(), "role"
            ),
            null
    );
  }

  /**
   * Получение пользователя.
   *
   * @param id идентификатор
   *
   * @return user пользователь
   */
  @GetMapping(path = "/{id}")
  @Async
  public CompletableFuture<UserResponse> get(@PathVariable Long id) {
    return ControllerHelper.customRequest(
            getUserById,
            new GetUserById.Params(id, "id"),
            UserResponse::new
    );
  }

  /**
   * Получение всех пользователей.
   *
   * @return list список пользователей
   */
  @GetMapping
  @Async
  public CompletableFuture<Iterable<UserResponse>> getAll() {
    return ControllerHelper.customRequest(
      getAllUsers,
      null,
      users -> users.stream()
              .map(UserResponse::new)
              .collect(Collectors.toList())
    );
  }

  /**
   * Обновление пользователя.
   *
   * @param id идентификатор
   */
  @PutMapping(path = "/{id}")
  @Async
  public void update(@PathVariable Long id, @RequestBody UserRequest userRequest) {
    ControllerHelper.customRequest(
            updateUserById,
            new UpdateUserById.Params(
                    id, "id",
                    userRequest.getName(), "name",
                    userRequest.getEmail(), "email",
                    userRequest.getPassword(), "password",
                    userRequest.getAvatar(), "image",
                    userRequest.getRole(), "role"
            ),
            UserResponse::new
    );
  }

  /**
   * Удаление пользователя.
   *
   * @param id идентификатор
   */
  @DeleteMapping(path = "/{id}")
  @Async
  public CompletableFuture<Void> delete(@PathVariable Long id) {
    return ControllerHelper.customRequest(
            deleteUserById,
            new DeleteUserById.Params(id, "id"),
            null
    );
  }
}
