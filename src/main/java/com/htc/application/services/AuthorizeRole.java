package com.htc.application.services;

import com.htc.domain.entities.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * Роль для авторизации с использованием Spring Security.
 */
@RequiredArgsConstructor
public class AuthorizeRole implements GrantedAuthority {
  private final Role role;

  @Override
  public String getAuthority() {
    return role.name();
  }
}
