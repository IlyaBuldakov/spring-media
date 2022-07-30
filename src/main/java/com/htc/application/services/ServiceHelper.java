package com.htc.application.services;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;

/**
 * Вспомогательный класс для сервисов.
 */
public class ServiceHelper {

  /**
   * Получение представления прав доступа
   * в виде строк (permissions).
   *
   * @return Уникальное множество прав доступа.
   */
  public static Set<String> getPermissions(Collection<? extends GrantedAuthority> authorities) {
    return authorities
            .parallelStream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toSet());
  }
}