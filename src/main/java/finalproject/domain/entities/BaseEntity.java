package finalproject.domain.entities;

import finalproject.domain.entities.user.User;

/**
 * Базовая сущность.
 */
public class BaseEntity {
  private int id;

  @Override
  public boolean equals(Object ob) {
    if (ob == null) {
      return false;
    }
    if (this == ob) {
      return true;
    }
    if (ob instanceof User other) {
      return this.id == other.getId();
    }
    return false;
  }


  @Override
  public int hashCode() {
    return id;
  }
}
