package finalproject.domain.entities.user;

import lombok.Getter;

/**
 * Роль пользователя.
 */

public enum Role {
    ADMIN(0, "Administrator"),
    MANAGER(1, "Manager"),
    CONTENT_MAKER(2, "ContentMaker");
    final @Getter int id;
    final @Getter String name;
    Role(int id, String name) {
      this.id = id;
      this.name = name;
    }
  }
