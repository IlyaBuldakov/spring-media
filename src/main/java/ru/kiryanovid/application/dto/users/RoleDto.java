package ru.kiryanovid.application.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.kiryanovid.domain.entity.users.User;

/**
 * Представление сущности пользователя роли.
 */
@AllArgsConstructor
public class RoleDto {
  /**
  * Идентификатор пользователя.
  */
  @Getter private Integer id;

  /**
  * Роль пользователя.
  */
  @Getter private String role;

  public RoleDto(User user) {
    this.id = user.getRole().getId();
    this.role = user.getRole().getName();
  }
}
