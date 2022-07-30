package com.htc.application.security;

import com.htc.domain.entities.user.Role;
import org.springframework.security.core.GrantedAuthority;

/**
 * Представление права доступа по роли для Spring Security.
 */
public class RoleGrantedAuthority implements GrantedAuthority {

  /**
   * Роль пользователя.
   */
  private final Role role;

  public RoleGrantedAuthority(Role role) {
    this.role = role;
  }

  @Override
  public String getAuthority() {
    return role.name();
  }
}