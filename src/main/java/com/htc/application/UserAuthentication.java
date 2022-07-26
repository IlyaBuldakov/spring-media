package com.htc.application;

import com.htc.domain.entities.User;
import java.util.Collection;
import java.util.Set;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class UserAuthentication implements Authentication {
  private boolean authenticated = true;
  private final int id;
  private final Set<UserGrantedAuthority> authorities;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getDetails() {
    return this.id;
  }

  @Override
  public Object getPrincipal() {
    return null;
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

  public UserAuthentication(int id, Set<UserGrantedAuthority> authorities) {
    this.id = id;
    this.authorities = authorities;
  }

  public record UserGrantedAuthority(User.Role role) implements GrantedAuthority {
    @Override
    public String getAuthority() {
      return role.toString();
    }
  }
  }
