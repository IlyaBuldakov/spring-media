package com.htc.application.controllers;

import com.htc.application.dto.user.UserRequest;
import com.htc.application.dto.user.UserResponse;
import com.htc.application.services.UsersService;
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

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Контроллер для пользователей.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UsersController {

    UsersService usersService;

    @GetMapping
    @Async
    public CompletableFuture<List<UserResponse>> getAllUsers() {
        return usersService.getAll();
    }

    @GetMapping("/{id}")
    @Async
    public CompletableFuture<UserResponse> getUserById(@PathVariable("id") String id) {
        return usersService.getById(id);
    }

    @PostMapping
    @Async
    public CompletableFuture<UserResponse> createUser(@RequestBody UserRequest user) {
        return usersService.create(user);
    }

    @PutMapping("/{id}")
    @Async
    public CompletableFuture<UserResponse> updateUser(@RequestBody UserRequest user,
                                                      @PathVariable("id") String id) {
        return usersService.update(user, id);
    }

    @DeleteMapping("/{id}")
    @Async
    public CompletableFuture<Void> deleteUser(@PathVariable("id") String id) {
        return usersService.delete(id);
    }
}
