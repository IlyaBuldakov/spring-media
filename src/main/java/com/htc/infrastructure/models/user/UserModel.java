package com.htc.infrastructure.models.user;

import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.entities.utility.parameters.user.UserEmail;
import com.htc.domain.entities.utility.parameters.user.UserImage;
import com.htc.domain.entities.utility.parameters.user.UserName;
import com.htc.domain.entities.utility.parameters.user.UserPassword;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import lombok.AllArgsConstructor;

/**
 * Представление сущности в СУБД.
 */
@Entity
@Table(name = "USERS")
@AllArgsConstructor
public class UserModel implements User {
  @javax.persistence.Id
  @GeneratedValue
  Long id;

  @Column(name = "name")
  String name;

  @Column(name = "password")
  String password;

  @Column(name = "email")
  String email;

  @Column(name = "image")
  String image;

  @Column(name = "role")
  String role;

  @Override
  public Id getId() {
    return Id.create(this.id).get();
  }

  @Override
  public UserName getName() {
    return UserName.create(this.name).get();
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
