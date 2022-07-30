package com.htc.application.controllers;

import com.htc.application.dto.user.UserRequest;
import com.htc.application.dto.user.UserResponse;
import com.htc.application.services.UsersService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для пользователей.
 */
@RestController
@RequestMapping("/api/users")
public class UsersController {

  public UsersController(UsersService usersService) {
    this.usersService = usersService;
  }

  UsersService usersService;

  /**
   * Получение всех пользователей.
   *
   * @return Список пользователей.
   */
  @GetMapping
  @Async
  public CompletableFuture<List<UserResponse>> getAllUsers() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    return usersService.getAll(securityContext.getAuthentication().getAuthorities());
  }

  /**
   * Получить пользователя по идентификатору.
   *
   * @param id Идентификатор пользователя.
   * @return Представление пользователя.
   */
  @GetMapping("/{id}")
  @Async
  public CompletableFuture<UserResponse> getUserById(@PathVariable("id") String id) {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    return usersService.getById(
            securityContext.getAuthentication().getAuthorities(),
            id);
  }

  /**
   * Создание пользователя.
   *
   * @param user Представление пользователя (запрос)
   * @return void.
   */
  @PostMapping
  @Async
  public CompletableFuture<Void> createUser(@RequestBody UserRequest user) {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    return usersService.create(
            securityContext.getAuthentication().getAuthorities(),
            user);
  }

  /**
   * Обновление пользователя по идентификатору.
   *
   * @param user Представление пользователя (запрос).
   * @param id   Идентификатор пользователя.
   * @return void.
   */
  @PutMapping("/{id}")
  @Async
  public CompletableFuture<Void> updateUser(@RequestBody UserRequest user,
                                            @PathVariable("id") String id) {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    return usersService.update(
            securityContext.getAuthentication().getAuthorities(),
            user,
            id);
  }

  /**
   * Удаление пользователя по идентификатору.
   *
   * @param id Идентификатор пользователя.
   * @return void.
   */
  @DeleteMapping("/{id}")
  @Async
  public CompletableFuture<Void> deleteUser(@PathVariable("id") String id) {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    return usersService.delete(
            securityContext.getAuthentication().getAuthorities(),
            id);
  }
}
