package com.htc.application.controllers;

import com.htc.application.dtos.exceptions.InternalServerErrorResponse;
import com.htc.application.dtos.exceptions.InvalidValuesResponse;
import com.htc.application.dtos.exceptions.NotFoundResponse;
import com.htc.application.dtos.exceptions.UnauthorizedResponse;
import com.htc.application.dtos.user.UserResponse;
import com.htc.domain.usecases.user.AddUser;
import com.htc.domain.usecases.user.DeleteUserById;
import com.htc.domain.usecases.user.GetAllUsers;
import com.htc.domain.usecases.user.GetUserById;
import com.htc.domain.usecases.user.SearchUsers;
import com.htc.domain.usecases.user.UpdateUser;
import com.htc.utility.ControllerHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  private UpdateUser updateUser;
  private DeleteUserById deleteUserById;
  private GetUserById getUserById;
  private GetAllUsers getAllUsers;
  private SearchUsers searchUsers;

  /**
   * Добавление пользователя.
   */
  @PostMapping
  public void add() {
    throw new UnsupportedOperationException("Метод не реализован");
  }

  /**
   * Получение пользователя.
   *
   * @param id идентификатор
   * @return user пользователь
   */
  @GetMapping(path = "/{id}")
  @Async
  @Operation(
          summary = "Получить пользователя по идентификатору.",
          responses = {
              @ApiResponse(
                      responseCode = "200",
                      content = @Content(
                              mediaType = "application/json",
                              schema = @Schema(implementation = UserResponse.class))),
              @ApiResponse(
                      responseCode = "400",
                      content = @Content(
                              mediaType = "application/json",
                              schema = @Schema(implementation = InvalidValuesResponse.class))),
              @ApiResponse(
                      responseCode = "401",
                      content = @Content(
                              mediaType = "application/json",
                              schema = @Schema(implementation = UnauthorizedResponse.class))),
              @ApiResponse(
                      responseCode = "404",
                      content = @Content(
                              mediaType = "application/json",
                              schema = @Schema(implementation = NotFoundResponse.class))),
              @ApiResponse(
                      responseCode = "500",
                      content = @Content(
                              mediaType = "application/json",
                              schema = @Schema(implementation = InternalServerErrorResponse.class)))
          }
  )
  public CompletableFuture<UserResponse> get(@PathVariable String id) {
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
  public CompletableFuture<Iterable<UserResponse>> getAll() {
    return ControllerHelper.customRequest(
      getAllUsers,
      null,
      users -> StreamSupport
              .stream(users.spliterator(), false)
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
  public void update(@PathVariable int id) {
    throw new UnsupportedOperationException("Метод не реализован");
  }

  /**
   * Удаление пользователя.
   *
   * @param id идентификатор
   */
  @DeleteMapping(path = "/{id}")
  public void delete(@PathVariable int id) {
    throw new UnsupportedOperationException("Метод не реализован");
  }
}
