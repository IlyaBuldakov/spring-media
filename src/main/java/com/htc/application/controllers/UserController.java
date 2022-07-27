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
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@RestController
@SecurityRequirement(name = "JWT")
@Tags(@Tag(name = "Пользователи"))
@RequestMapping(path = "api/users")
public class UserController {
  private AddUser addUser;
  private UpdateUserById updateUserById;
  private DeleteUserById deleteUserById;
  private GetUserById getUserById;
  private GetAllUsers getAllUsers;

  /**
   * Добавление пользователя.
   */
  @Operation(summary = "Добавить нового пользователя.")
  @PostMapping
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
   * @return user пользователь
   */
  @Operation(summary = "Получить пользователя по идентификатору.")
  @GetMapping(path = "/{id}")
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
  @Operation(summary = "Получить список всех пользователей.")
  @GetMapping
  public CompletableFuture<List<UserResponse>> getAll() {
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
  @Operation(summary = "Обновить пользователя по идентификатору.")
  @PutMapping(path = "/{id}")
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
  @Operation(summary = "Удалить пользователя по идентификатору.")
  @DeleteMapping(path = "/{id}")
  public CompletableFuture<Void> delete(@PathVariable Long id) {
    return ControllerHelper.customRequest(
            deleteUserById,
            new DeleteUserById.Params(id, "id"),
            null
    );
  }
}
