package ru.kiryanovid.infrastructure.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.kiryanovid.domain.entity.users.Role;

/**
 * Модель пользователя для СУБД.
 */
@Entity
@Table(name = "users")
@AllArgsConstructor
public class UserModel {
  /**
   * Пользователь.
   */

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private @Getter Integer id;

  private @Getter String name;

  private @Getter String email;

  private @Getter String password;

  private @Getter String image;

  @Enumerated(EnumType.STRING)
  private @Getter Role role;

  protected UserModel() {
  }

  public UserModel(Integer id) {
    this.id = id;
  }
}

