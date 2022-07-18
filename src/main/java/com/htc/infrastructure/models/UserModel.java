package com.htc.infrastructure.models;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.user.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import lombok.AllArgsConstructor;

/**
 * Модель пользователя для СУБД.
 */
@Entity
@Table(name = "Users")
@AllArgsConstructor
public class UserModel implements User {
  /**
   * Идентификатор пользователя.
   */
  @javax.persistence.Id
  @GeneratedValue
  @Column(name = "user_id")
  private Integer userId;

  @Override
  public Id getId() {
    return Id.create(this.userId).get();
  }

  /**
   * Имя пользователя.
   */
  private String name;

  @Override
  public Name getName() {
    return Name.create(this.name).get();
  }

  /**
   * Электронная почта пользователя.
   */
  private String email;

  @Override
  public Email getEmail() {
    return Email.create(this.email).get();
  }

  /**
   * Пароль пользователя.
   */
  private String password;

  @Override
  public Password getPassword() {
    return Password.create(this.password).get();
  }

  /**
   * Изображение пользователя.
   */
  private String image;

  @Override
  public Image getImage() {
    return Image.create(this.image).get();
  }

  /**
   * Роль пользователя, см. {@link Role}.
   */
  @Enumerated(EnumType.STRING)
  private Role role;

  @Override
  public Role getRole() {
    return this.role;
  }

  protected UserModel() {
  }

  public UserModel(
          Id id,
          Name name,
          Email email,
          Password password,
          Image image,
          Role role) {
    this.userId = id.getValue();
    this.name = name.getValue();
    this.email = email.getValue();
    this.password = password.getValue();
    this.image = image.getValue();
    this.role = role;
  }

  public UserModel(
          Name name,
          Email email,
          Password password,
          Image image,
          Role role) {
    this(Id.create(0).get(), name, email, password, image, role);
  }
}
