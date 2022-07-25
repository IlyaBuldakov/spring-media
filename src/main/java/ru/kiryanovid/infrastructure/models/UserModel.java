package ru.kiryanovid.infrastructure.models;

import javax.persistence.Column;
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
import ru.kiryanovid.domain.entity.users.User;

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
  @Column(unique = true, nullable = false)
  @Getter
  private Integer id;

  @Column(nullable = false)
  @Getter
  private String name;

  @Column(unique = true, nullable = false)
  @Getter
  private String email;

  @Column(nullable = false)
  @Getter
  private String password;

  @Getter
  private String image;

  @Enumerated(EnumType.STRING)
  @Getter
  private Role role;

  protected UserModel() {
  }

  public UserModel(Integer id) {
    this.id = id;
  }

  /**
  * Конвертация модели пользователя в сущность пользователя.
  *
  * @param userModel Модель пользователя
  *
  * @return Пользователь
  */
  public static User convertUserModelToEntityUser(UserModel userModel) {
    return User.create(userModel.getId(),
                userModel.getName(),
                userModel.getEmail(),
                userModel.getPassword(),
                userModel.getImage(),
                userModel.getRole()).get();
  }
}

