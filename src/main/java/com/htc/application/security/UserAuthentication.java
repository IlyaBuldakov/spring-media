package com.htc.application.security;

import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * Реализация Authentication.
 */
public class UserAuthentication extends UsernamePasswordAuthenticationToken {

  /**
   * Идентификатор пользователя.
   */
  private final int id;

  public UserAuthentication(int id, Object principal, Object credentials,
                            Collection<? extends GrantedAuthority> authorities) {
    super(principal, credentials, authorities);
    this.id = id;
  }

  @Override
  public Object getDetails() {
    return id;
  }
}