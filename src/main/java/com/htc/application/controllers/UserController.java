package com.htc.application.controllers;

import com.htc.domain.entities.user.User;
import com.htc.domain.usecases.user.AddUser;
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
  }

  /**
   * Получение пользователя.
   *
   * @param id - идентификатор
   * @return user - пользователь
   * @throws ExecutionException - исключение выполнения
   * @throws InterruptedException - исключение прерывания
   */
  @GetMapping(path = "/{id}")
  public User get(@PathVariable int id) throws ExecutionException, InterruptedException {
    return getUserById.execute(id)
            .get()
            .get();
  }

  /**
   * Получение всех пользователей.
   *
   * @return list - список пользователей
   * @throws ExecutionException - исключение выполнения
   * @throws InterruptedException - исключение прерывания
   */
  @GetMapping
  public Iterable<User> getAll() throws ExecutionException, InterruptedException {
    return getAllUsers.execute(null)
            .get()
            .get();
  }

  /**
   * Обновление пользователя.
   *
   * @param id - идентификатор
   */
  @PutMapping(path = "/{id}")
  public void update(@PathVariable int id) {
  }

  /**
   * Удаление пользователя.
   *
   * @param id - идентификатор
   */
  @DeleteMapping(path = "/{id}")
  public void delete(@PathVariable int id) {
  }
}
