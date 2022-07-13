package com.htc.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.htc.domain.entities.attributes.Attribute;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.InvalidValue;
import io.vavr.control.Either;
import lombok.Getter;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * Пользователь.
 */
public interface User {
  /**
   * Идентификатор пользователя.
   *
   * @return id Идентификатор пользователя.
   */
  Id getId();

  /**
   * Имя пользователя.
   *
   * @return id Имя пользователя.
   */
  Name getName();

  /**
   * Электронная почта пользователя.
   *
   * @return id Электронная почта пользователя.
   */
  Email getEmail();

  /**
   * Пароль пользователя.
   *
   * @return id Пароль пользователя.
   */
  Password getPassword();

  /**
   * Изображение пользователя.
   *
   * @return id Изображение пользователя.
   */
  Image getImage();

  /**
   * Роль пользователя, см. {@link Role}.
   *
   * @return id Роль пользователя.
   */
  Role getRole();

  /**
   * Электронная почта пользователя.
   */
  final class Email extends Attribute<String> {
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
  final class Image extends Attribute<String> {
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
  final class Name extends Attribute<String> {
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
  final class Password extends Attribute<String> {
    /**
     * Проверяет входные данные на корректность и создаёт пароль пользователя.
     *
     * <p>
     * Корректный пароль:
     * 1. длина пароля от 8 до 20 символов (включительно),
     * 2. пароль содержащий символы латинского алфавита обоих регистров,
     * 3. пароль содержим цифры,
     * 4. пароль может содержать знак подчёркивания.
     * </p>
     *
     * <p>
     * Пример корректного пароля: {@code abcdEFG1234}
     * Пример некорректного пароля: {@code abc}
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

  /**
   * Роль пользователя.
   */
  enum Role {
    /**
     * Администратор.
     */
    @JsonProperty("admin")
    ADMIN(1, "Администратор"),
    /**
     * Менеджер.
     */
    @JsonProperty("manager")
    MANAGER(2, "Менеджер"),
    /**
     * Контент-мейкер.
     */
    @JsonProperty("contentMaker")
    CONTENT_MAKER(3, "Контент-мейкер");

    /**
     * Идентификатор роли.
     *
     * @return id Идентификатор роли.
     */
    @SuppressWarnings("JavadocDeclaration")
    private final @Getter int id;

    /**
     * Название роли.
     *
     * @return name Название роли.
     */
    @SuppressWarnings("JavadocDeclaration")
    private final @Getter String name;

    Role(int id, String name) {
      this.id = id;
      this.name = name;
    }
  }
}
