package com.htc.application.controllers;

import com.htc.application.dto.user.UserRequest;
import com.htc.application.dto.user.UserResponse;
import com.htc.application.services.UsersService;
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

import java.util.List;
import java.util.concurrent.CompletableFuture;

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

  @GetMapping
  @Async
  public CompletableFuture<List<UserResponse>> getAllUsers() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    return usersService.getAll(securityContext.getAuthentication().getAuthorities());
  }

  @GetMapping("/{id}")
  @Async
  public CompletableFuture<UserResponse> getUserById(@PathVariable("id") String id) {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    return usersService.getById(
            securityContext.getAuthentication().getAuthorities(),
            id);
  }

  @PostMapping
  @Async
  public CompletableFuture<Void> createUser(@RequestBody UserRequest user) {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    return usersService.create(
            securityContext.getAuthentication().getAuthorities(),
            user);
  }

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

  @DeleteMapping("/{id}")
  @Async
  public CompletableFuture<Void> deleteUser(@PathVariable("id") String id) {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    return usersService.delete(
            securityContext.getAuthentication().getAuthorities(),
            id);
  }
}
