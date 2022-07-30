package com.htc.application.security;

import com.htc.application.dto.errors.InternalServerErrorResponse;
import com.htc.domain.usecases.user.GetUserByEmail;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * Реализация вспомогательного интерфейса Spring Security.
 * Позволяет адаптерам найти пользователя по E-mail (см. {@link DaoAuthenticationProvider})
 * (в контексте Spring Security - username).
 */
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  GetUserByEmail getUserByEmail;

  /**
   * Инструкция для провайдера: каким образом нужно
   * получать пользователя при аутентификации.
   *
   * @param email E-mail пользователя.
   * @return {@link UserSecurity Представление} пользователя для Spring Security.
   * @throws UsernameNotFoundException Пользователь не найден.
   */
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    var result = getUserByEmail.execute(email);
    try {
      var user = result.get().get();
      return new UserSecurity(
              user.getId(), user.getEmail(), user.getPassword(),
              Set.of(new RoleGrantedAuthority(user.getRole())
              ), true);
    } catch (ExecutionException | InterruptedException e) {
      throw new InternalServerErrorResponse();
    }
  }
}
