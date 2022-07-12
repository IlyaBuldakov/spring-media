package com.htc.domain.entities.user;

import com.htc.domain.entities.attributes.Attribute;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.InvalidValue;
import io.vavr.control.Either;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * Пользователь.
 */
public class User {
  /**
   * Идентификатор пользователя.
   */
  private Id id;

  /**
   * Электронная почта пользователя.
   */
  private Email email;
  /**
   * Пароль пользователя.
   */
  private Password password;
  /**
   * Имя пользователя.
   */
  private Name name;
  /**
   * Изображение пользователя.
   */
  private Image image;
  /**
   * Роль пользователя, см. {@link Role}.
   */
  private Role role;

  /**
   * Идентификатор пользователя.
   *
   * @return Идентификатор пользователя.
   */
  public int getId() {
    return id.getValue();
  }

  /**
   * Электронная почта пользователя.
   *
   * @return Электронная почта пользователя.
   */
  public String getEmail() {
    return email.getValue();
  }

  /**
   * Пароль пользователя.
   *
   * @return Пароль пользователя.
   */
  public String getPassword() {
    return password.getValue();
  }

  /**
   * Имя пользователя.
   *
   * @return Имя пользователя.
   */
  public String getName() {
    return name.getValue();
  }

  /**
   * Изображение пользователя.
   *
   * @return Изображение пользователя.
   */
  public String getImage() {
    return image.getValue();
  }

  /**
   * Роль пользователя, см. {@link Role}.
   *
   * @return Роль пользователя.
   */
  public Role getRole() {
    return role;
  }

  private User() {
  }

  /**
   * Создаёт пользователя и проверяет данные на корректность.
   *
   * @param id       Идентификатор.
   * @param name     Имя.
   * @param email    Электронная почта.
   * @param password Пароль.
   * @param image    Изображение.
   * @param role     Роль.
   */
  public User(Id id, Name name, Email email, Password password, Image image, Role role) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.image = image;
    this.role = role;

  }

  /**
   * Электронная почта пользователя.
   */
  public static final class Email extends Attribute<String> {
    /**
     * Проверяет входные данные на корректность и создаёт электронную почту пользователя.
     *
     * @param value Входные данные.
     * @return Электронная почта пользователя или ошибка.
     */
    public static Either<InvalidValue, Email> create(String value) {
      if (value.length() == 0 || !EmailValidator.getInstance().isValid(value)) {
        return Either.left(InvalidValue.INVALID_USER_EMAIL);
      }

      return Either.right(new Email(value));
    }

    private Email(String value) {
      super(value);
    }
  }

  /**
   * Изображение пользователя.
   */
  public static final class Image extends Attribute<String> {
    /**
     * Проверяет входные данные на корректность и создаёт изображение пользователя.
     * Изображение пользователя должно быть представлено в кодировке Base64.
     *
     * @param value Входные данные.
     * @return Изображение пользователя или ошибка.
     */
    public static Either<InvalidValue, Image> create(String value) {
      if (value.length() == 0 || !Base64.isBase64(value)) {
        return Either.left(InvalidValue.INVALID_USER_IMAGE);
      }

      var userImage = new Image(value);
      return Either.right(userImage);
    }

    private Image(String value) {
      super(value);
    }
  }

  /**
   * Имя пользователя.
   */
  public static final class Name extends Attribute<String> {
    /**
     * Проверяет входные данные на корректность и создаёт имя пользователя.
     * Имя пользователя не должно быть пустой строкой и не должно быть длиннее 32 символов
     *
     * @param value Входные данные.
     * @return Изображение пользователя или ошибка.
     */
    public static Either<InvalidValue, Name> create(String value) {
      if (value.length() == 0 || value.length() > 32) {
        return Either.left(InvalidValue.INVALID_USER_NAME);
      }

      var userName = new Name(value);
      return Either.right(userName);
    }

    private Name(String value) {
      super(value);
    }
  }

  /**
   * Пароль пользователя.
   */
  public static final class Password extends Attribute<String> {
    /**
     * Проверяет входные данные на корректность и создаёт пароль пользователя.
     *
     * </p>
     *
     * @param value Входные данные.
     * @return Пароль пользователя или ошибка.
     */
    public static Either<InvalidValue, Password> create(String value) {
      if (!value.matches("\\w{8,20}")
              || !value.matches(".*\\d+.*")
              || value.toLowerCase().equals(value)
              || value.toUpperCase().equals(value)) {
        return Either.left(InvalidValue.INVALID_USER_PASSWORD);
      }

      return Either.right(new Password(value));
    }

    private Password(String value) {
      super(value);
    }
  }


}