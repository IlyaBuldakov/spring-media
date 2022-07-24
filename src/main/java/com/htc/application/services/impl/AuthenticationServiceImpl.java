package com.htc.application.services.impl;

import com.htc.application.dto.errors.InternalServerErrorResponse;
import com.htc.application.dto.login.LoginRequest;
import com.htc.application.dto.login.LoginResponse;
import com.htc.application.services.AuthenticationService;
import com.htc.application.services.JwtService;
import com.htc.application.services.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    JwtService jwtService;
    UsersService usersService;

    @Override
    public CompletableFuture<LoginResponse> login(LoginRequest loginRequest) {
        try {
            var user  = usersService.getByEmail(loginRequest.getEmail()).get();
            return CompletableFuture.completedFuture(
                    jwtService.createPairOfTokens(user.getId(), user.getRole().getRole()));
        } catch (InterruptedException | ExecutionException e) {
            throw new InternalServerErrorResponse();
        }
    }
}