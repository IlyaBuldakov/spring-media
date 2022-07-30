package com.htc.infrastructure.mappers;

import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Представление пользователя для БД.
 */
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class UserMapper implements User {

  public UserMapper(String name, String password, String email, String avatar, Role role) {
    this.name = name;
    this.password = password;
    this.email = email;
    this.avatar = avatar;
    this.role = role;
  }

  public UserMapper(int id, String name, String password, String email, String avatar, Role role) {
    this.id = id;
    this.name = name;
    this.password = password;
    this.email = email;
    this.avatar = avatar;
    this.role = role;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  public @Getter Integer id;

  @Column(name = "name")
  public @Getter String name;

  @Column(name = "password")
  public @Getter String password;

  @Column(name = "email")
  public @Getter String email;

  @Column(name = "avatar")
  public @Getter String avatar;

  @Enumerated(EnumType.STRING)
  private @Getter Role role;
}
