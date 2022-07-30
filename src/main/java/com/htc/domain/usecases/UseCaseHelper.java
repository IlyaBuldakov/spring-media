package com.htc.domain.usecases;

import com.htc.domain.entities.user.Role;
import java.util.Set;

/**
 * Вспомогательный класс для UseCase'ов.
 */
public class UseCaseHelper {

  /**
   * Логика проверки прав доступа пользователя по роли.
   *
   * @param permissions Права доступа.
   * @param roles       Массив ролей.
   * @return boolean.
   */
  public static boolean hasRolePermissions(Set<String> permissions, Role... roles) {
    int localRoleCounter = 0;
    for (String permission : permissions) {
      Role expectedRole = Role.lookup(permission);
      if (expectedRole == roles[localRoleCounter++]) {
        return true;
      }
    }
    return false;
  }
}