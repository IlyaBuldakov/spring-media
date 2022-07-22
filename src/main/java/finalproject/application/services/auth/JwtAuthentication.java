package finalproject.application.services.auth;

import java.util.Collection;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * Класс аутентификации пользователей.
 */
@Getter
@Setter
public class JwtAuthentication implements Authentication {

  private int userId;
  private boolean authenticated;
  private String email;
  private String name;
  private Set<AuthRole> roles;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles;
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getDetails() {
    return userId;
  }

  @Override
  public Object getPrincipal() {
    return email;
  }

  @Override
  public boolean isAuthenticated() {
    return authenticated;
  }

  @Override
  public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    this.authenticated = isAuthenticated;
  }

  @Override
  public String getName() {
    return name;
  }



}
