package finalproject.domain.entities.user;

import lombok.Getter;

import java.util.EnumMap;

/**
 * Роль пользователя.
 */

public enum Role {
    ADMIN(0, "admin"),
    MANAGER(1, "manager"),
    CONTENT_MAKER(2, "contentMaker");
    final @Getter int id;
    final @Getter String name;
    Role(int id, String name) {
      this.id = id;
      this.name = name;
    }
    public static Role getRoleById(int id) {
      return Role.values()[id];
    }
  }
