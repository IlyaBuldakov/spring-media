package com.htc.infrastructure.models.user;

import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.EntityName;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.entities.utility.parameters.user.UserEmail;
import com.htc.domain.entities.utility.parameters.user.UserImage;
import com.htc.domain.entities.utility.parameters.user.UserPassword;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import lombok.AllArgsConstructor;

/**
 * Представление сущности пользователя в СУБД.
 */
@Entity
@Table(name = "users")
@AllArgsConstructor
public class UserModel implements User {
  @javax.persistence.Id
  @GeneratedValue
  @Column(name = "id", unique = true, nullable = false)
  Long userId;

  @Column(nullable = false)
  String name;

  //TODO реализовать шифрование пароля (bcrypt)
  @Column(nullable = false)
  String password;

  @Column(unique = true, nullable = false)
  String email;

  String image;

  @Column(nullable = false)
  String role;

  @Override
  public Id getId() {
    return Id.create(this.userId).get();
  }

  @Override
  public EntityName getName() {
    return EntityName.create(this.name).get();
  }

  @Override
  public UserPassword getPassword() {
    return UserPassword.create(this.password).get();
  }

  @Override
  public UserEmail getEmail() {
    return UserEmail.create(this.email).get();
  }

  @Override
  public UserImage getAvatar() {
    return UserImage.create(this.image).get();
  }

  @Override
  public Role getRole() {
    return Role.getFromName(this.role);
  }

  protected UserModel() {}
}
