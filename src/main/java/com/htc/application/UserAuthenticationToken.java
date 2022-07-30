package com.htc.application;

import com.htc.domain.entities.Id;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class UserAuthenticationToken implements Authentication {
  @Getter
  private final Id id;

  private boolean authenticated = true;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
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
