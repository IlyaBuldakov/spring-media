package com.htc.application.services;

import com.htc.domain.entities.utility.parameters.Id;
import java.util.Collection;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * Информация об аутентифицированном пользователе для Spring Security.
 */
@AllArgsConstructor
public class UserAuthenticationToken implements Authentication {
  private final @Getter Id id;
  private final @Getter Set<AuthorizeRole> roles;

  private boolean authenticated = true;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return getRoles();
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getDetails() {
    return null;
  }

  @Override
  public Object getPrincipal() {
    return id;
  }

  @Override
  public boolean isAuthenticated() {
    return this.authenticated;
  }

  @Override
  public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    this.authenticated = isAuthenticated;
  }

  @Override
  public String getName() {
    return null;
  }
}
