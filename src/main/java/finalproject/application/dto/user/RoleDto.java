package finalproject.application.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Роль пользователя.
 */

public enum RoleDto {
    ADMIN(0, "admin"),
    MANAGER(1, "manager"),
    CONTENT_MAKER(2, "contentMaker");
    final @Getter int id;
    final @Getter String name;
    RoleDto(int id, String name) {
      this.id = id;
      this.name = name;
    }

  }


