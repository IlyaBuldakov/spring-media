package com.htc.application.services;

import com.htc.application.dto.login.LoginRequest;
import com.htc.application.dto.login.LoginResponse;

import java.util.concurrent.CompletableFuture;

public interface AuthenticationService {

  CompletableFuture<LoginResponse> login(LoginRequest loginRequest);

  CompletableFuture<LoginResponse> getNewAccessToken(String refreshToken);
}