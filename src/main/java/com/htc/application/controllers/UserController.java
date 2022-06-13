package com.htc.application.controllers;

import com.htc.application.dtos.user.UserResponse;
import com.htc.domain.usecases.user.AddUser;
import com.htc.domain.usecases.user.DeleteUserById;
import com.htc.domain.usecases.user.GetAllUsers;
import com.htc.domain.usecases.user.GetUserById;
import com.htc.domain.usecases.user.SearchUsers;
import com.htc.domain.usecases.user.UpdateUser;
import com.htc.utility.CustomExceptionsHelper;
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
  public CompletableFuture<UserResponse> get(@PathVariable int id) {
    return getUserById.execute(id).thenApplyAsync(
            users -> users.map(UserResponse::new)
                    .getOrElseThrow(() -> CustomExceptionsHelper.getExceptionFromLeft(users))
    );
  }

  /**
   * Получение всех пользователей.
   *
   * @return list список пользователей
   */
  @GetMapping
  public CompletableFuture<Iterable<UserResponse>> getAll() {
    return getAllUsers.execute(null).thenApplyAsync(
            iterUsers -> iterUsers.map(
                    users -> StreamSupport
                            .stream(users.spliterator(), false)
                            .map(UserResponse::new)
                            .collect(Collectors.toList()))
                    .getOrElseThrow(() -> CustomExceptionsHelper.getExceptionFromLeft(iterUsers))
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
