package finalproject.application.services.auth;

import finalproject.domain.entities.user.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * Адаптер роли пользователя для использования в Spring Security.
 */
@AllArgsConstructor
public class AuthRole implements GrantedAuthority {
  Role role;


  @Override
  public String getAuthority() {

    return role.name();

  }
}
