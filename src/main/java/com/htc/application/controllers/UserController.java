package com.htc.application.controllers;

import com.htc.domain.entities.user.User;
import com.htc.domain.usecases.user.CreateUser;
import com.htc.domain.usecases.user.DeleteUserById;
import com.htc.domain.usecases.user.GetAllUsers;
import com.htc.domain.usecases.user.GetUserById;
import com.htc.domain.usecases.user.SearchUsers;
import com.htc.domain.usecases.user.UpdateUser;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  private SearchUsers searchUsers;

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
  public User get(@PathVariable int id) throws ExecutionException, InterruptedException {
    return getUserById.execute(id).get().get();
  }

  /**
   * Возвращает список всех пользователей.
   *
   * @return Список пользователей.
   * @throws ExecutionException   Ошибка выполнения запроса.
   * @throws InterruptedException Ошибка выполнения запроса.
   */
  @GetMapping
  public Iterable<User> getAll() throws ExecutionException, InterruptedException {
    return getAllUsers.execute(null).get().get();
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
