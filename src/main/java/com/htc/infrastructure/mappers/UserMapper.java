package com.htc.infrastructure.mappers;

import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
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
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;

/**
 * Представление сущности пользователя для БД.
 */
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class UserMapper implements User {

  /**
   * Конструктор без идентификатора.
   *
   * @param name     Имя пользователя.
   * @param password Пароль пользователя.
   * @param email    Электронна почта пользователя.
   * @param avatar   Аватар пользователя.
   * @param role     Роль пользователя.
   */
  public UserMapper(String name, String password, String email, String avatar, Role role) {
    this.name = name;
    this.password = password;
    this.email = email;
    this.avatar = avatar;
    this.role = role;
  }

  /**
   * Конструктор для обновления.
   *
   * @param id       Идентификатор пользователя.
   * @param name     Имя пользователя.
   * @param password Пароль пользователя.
   * @param email    Электронная почта пользователя.
   * @param avatar   Аватар пользователя.
   * @param role     Роль пользователя.
   */
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
  @Column(name = "id", unique = true)
  @Unique
  public @Getter Integer id;

  public @Getter String name;

  public @Getter String password;

  @Column(unique = true)
  public @Getter String email;

  public @Getter String avatar;

  @Enumerated(EnumType.STRING)
  private @Getter Role role;
}
