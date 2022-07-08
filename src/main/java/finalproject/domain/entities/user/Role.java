package finalproject.domain.entities.user;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashMap;

/**
 * Роль пользователя.
 */

public enum Role implements GrantedAuthority {
  ADMIN(0, "admin"),
  MANAGER(1, "manager"),
  CONTENT_MAKER(2, "contentMaker");
  final @Getter int id;
  final @Getter String name;

  Role(int id, String name) {
      this.id = id;
      this.name = name;
  }
  static final HashMap<String, Role> map = new HashMap<>();
  static {
    for (Role role : Role.values()) {
    map.put(role.getName(), role);
    }
  }
  public static Role getRoleByName(String name) {
    return map.get(name);
  }


  @Override
  public String getAuthority() {
    return this.name();
  }
}
