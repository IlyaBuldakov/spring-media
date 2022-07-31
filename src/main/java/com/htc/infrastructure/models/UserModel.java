package com.htc.infrastructure.models;

import com.htc.domain.entities.Entity;
import com.htc.domain.entities.User;
import com.htc.domain.entities.attributes.Id;
import com.htc.infrastructure.exception.InvalidDataException;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

/**
 * Модель пользователя для СУБД.
 */
@javax.persistence.Entity
@Table(name = "Users")
@AllArgsConstructor
public class UserModel implements Entity.Model<User> {
  /**
   * Идентификатор пользователя.
   */
  @javax.persistence.Id
  @GeneratedValue
  @Column(name = "id")
  private Integer userId;

  /**
   * Имя пользователя.
   */
  @NotNull
  private String name;

  /**
   * Электронная почта пользователя.
   */
  @NotNull
  @Column(unique = true)
  private String email;

  /**
   * Пароль пользователя.
   */
  @NotNull
  private String password;

  /**
   * Изображение пользователя.
   */
  @NotNull
  private String image;

  /**
   * Роль пользователя, см. {@link User.Role}.
   */
  @NotNull
  @Enumerated(EnumType.STRING)
  private User.Role role;


  @Override
  public User toEntity(){
    final var id = Id
        .create(this.userId)
        .getOrElseThrow(InvalidDataException::new);

    final var name = User.Name
        .create(this.name)
        .getOrElseThrow(InvalidDataException::new);

    final var email = User.Email
        .create(this.email)
        .getOrElseThrow(InvalidDataException::new);

    final var password = User.Password
        .create(this.password)
        .getOrElseThrow(InvalidDataException::new);

    final var image = User.Image
        .create(this.image)
        .getOrElseThrow(InvalidDataException::new);

    return new User(id, name, email, password, image, this.role);
  }

  protected UserModel() {
  }

  public UserModel(
      String name,
      String email,
      String password,
      String image,
      User.Role role) {
    this(0, name, email, password, image, role);
  }
}
