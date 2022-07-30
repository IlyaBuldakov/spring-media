package com.htc.application.services;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

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