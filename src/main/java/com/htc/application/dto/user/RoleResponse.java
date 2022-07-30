package com.htc.application.dto.user;

import com.htc.application.dto.BaseResponse;
import com.htc.domain.entities.user.Role;
import lombok.Getter;

/**
 * Представление сущности роли (ответ).
 */
public class RoleResponse implements BaseResponse {

  public RoleResponse(Role role) {
    this.id = role.getId();
    this.role = role;
  }

  /**
   * Идентификатор роли.
   */
  private final @Getter int id;

  /**
   * Имя роли.
   */
  private final @Getter Role role;
}
