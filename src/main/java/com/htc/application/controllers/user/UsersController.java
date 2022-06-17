package com.htc.application.controllers.user;

import com.htc.application.dto.user.UserResponse;
import com.htc.application.services.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Контроллер для пользователей
 *
 * @author IlyaBuldakov
 */
@Controller
@AllArgsConstructor
@RequestMapping("/api/users/")
public class UsersController {

    UsersService usersService;

    @GetMapping
    @Async
    public CompletableFuture<List<UserResponse>> getAllUsers() {
        return null;
    }

    @GetMapping("/{id}")
    @Async
    public CompletableFuture<UserResponse> getUserById(@PathVariable("id") String id) {
        return null;
    }

    @PostMapping
    @Async
    public CompletableFuture<UserResponse> createUser() {
        return null;
    }

    @PutMapping("/{id}")
    public CompletableFuture<UserResponse> updateUser(@PathVariable("id") String id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<UserResponse> deleteUser(@PathVariable("id") String id) {
        return null;
    }

}
