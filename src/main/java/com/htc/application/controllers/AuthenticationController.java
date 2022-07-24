package com.htc.application.controllers;

import com.htc.application.dto.login.LoginRequest;
import com.htc.application.dto.login.LoginResponse;
import com.htc.application.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/login")
    @Async
    public CompletableFuture<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }
}
