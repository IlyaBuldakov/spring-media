package com.htc.application.security;

import java.util.Collection;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Реализация контракта пользователя Spring Security.
 */
@AllArgsConstructor
public class UserSecurity implements UserDetails {

  /**
   * Идентификатор пользователя.
   */
  private int id;

  /**
   * E-mail пользователя.
   * (в контексте Spring Security - username, см. {@link UserDetailsServiceImpl})
   */
  private String email;

  /**
   * Пароль пользователя.
   */
  private String password;

  /**
   * Права пользователя.
   */
  private Set<GrantedAuthority> authorities;

  /**
   * Статус пользователя.
   */
  private boolean isActive;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return isActive;
  }

  @Override
  public boolean isAccountNonLocked() {
    return isActive;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return isActive;
  }

  @Override
  public boolean isEnabled() {
    return isActive;
  }
}
