package com.htc.application.controllers;

import com.htc.application.dto.user.UserResponse;
import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.usecases.user.CreateUser;
import com.htc.domain.usecases.user.DeleteUserById;
import com.htc.domain.usecases.user.GetAllUsers;
import com.htc.domain.usecases.user.GetUserById;
import com.htc.domain.usecases.user.UpdateUser;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
  public void create() {
  }

  /**
   * Возвращает пользователя.
   *
   * @param id Идентификатор пользователя.
   * @return Пользователь.
   * @throws ExecutionException   Ошибка выполнения запроса.
   * @throws InterruptedException Ошибка выполнения запроса.
   */
  @GetMapping(path = "/{id}")
  @Async
  public CompletableFuture<UserResponse> get(@PathVariable int id)
          throws ExecutionException, InterruptedException {
    return getUserById.execute(id)
            .thenApply(user -> user
                    .map(UserResponse::new)
                    .getOrElseThrow(failure -> switch (failure) {
                      case NotFound ignored -> new ResponseStatusException(HttpStatus.NOT_FOUND);
                      default -> new ResponseStatusException(
                              HttpStatus.INTERNAL_SERVER_ERROR, failure.getMessage());
                    }));

  }
  /**
   * Возвращает список всех пользователей.
   *
   * @return Список пользователей.
   * @throws ExecutionException   Ошибка выполнения запроса.
   * @throws InterruptedException Ошибка выполнения запроса.
   */
  @GetMapping
  public CompletableFuture<Iterable<UserResponse>> getAll() throws ExecutionException, InterruptedException {
    return getAllUsers.execute(null)
            .thenApply(users -> users
            .map(iterable  -> StreamSupport.stream(iterable.spliterator(), false)
                    .map(UserResponse::new)
                    .collect(Collectors.toList()))
            .getOrElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)));
  }

  /**
   * Обновляет данные пользователя.
   *
   * @param id Идентификатор пользователя.
   */
  @PutMapping(path = "/{id}")
  public void update(@PathVariable String id) {
  }

  /**
   * Удаляет пользователя.
   *
   * @param id Идентификатор пользователя.
   */
  @DeleteMapping(path = "/{id}")
  public void delete(@PathVariable String id) {
  }

}
