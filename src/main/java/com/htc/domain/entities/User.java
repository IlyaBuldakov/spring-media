package com.htc.domain.entities;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.util.Collection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * Пользователь.
 *
 * @param id Идентификатор пользователя.
 * @param name Имя пользователя.
 * @param email Электронная почта пользователя.
 * @param password Пароль пользователя.
 * @param image Изображения пользователя.
 * @param role Роль пользователя.
 */
public record User(
        Id id,
        User.Name name,
        User.Email email,
        User.Password password,
        Image image,
        Role role
) implements Entity {
  /**
   * Имя пользователя.
   */
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class Name extends BaseAttribute<String> {
    /**
     * Проверяет входные данные на корректность и создаёт имя пользователя.
     *
     * @param value Входные данные.
     * @return Ошибка или имя пользователя.
     */
    public static Either<Collection<Failure>, Name> create(@NonNull String value) {
      final var name = new Name();
      name.setValue(value);

      final var failures = name.validate();
      return failures.isEmpty()
              ? Either.right(name)
              : Either.left(failures);
    }

    /**
     * Проверяет имя пользователя на корректность.
     *
     * <p>Требования к имени пользователя:</p>
     * <ol>
     *   <li>Имя не должно быть короче 5 символов, см. {@link TooShort}.</li>
     *   <li>Имя не должно быть длиннее 40 символов, см. {@link TooLong}.</li>
     * </ol>
     *
     * @return Список ошибок.
     */
    @Override
    public Collection<Failure> validate() {
      final var value = super.getValue();
      return new ValidationResultBuilder()
              .addIf(new TooShort(), value.length() < 5)
              .addIf(new TooLong(), value.length() > 40)
              .build();
    }

    /**
     * Имя пользователя слишком короткое.
     */
    public record TooShort() implements Failure {}

    /**
     * Имя пользователя слишком длинное.
     */
    public record TooLong() implements Failure {}
  }

  /**
   * Электронная почта пользователя.
   */
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class Email extends BaseAttribute<String> {
    /**
     * Проверяет входные данные на корректность и создаёт электронную почту пользователя.
     *
     * @param value Входные данные.
     * @return Ошибка или электронная почта пользователя.
     */
    public static Either<Collection<Failure>, Email> create(@NonNull String value) {
      final var email = new Email();
      email.setValue(value);

      final var failures = email.validate();
      return failures.isEmpty()
              ? Either.right(email)
              : Either.left(failures);
    }

    /**
     * Проверяет электронную почту пользователя на корректность.
     *
     * <p>Требования к электронной почте пользователя:</p>
     * <ol>
     *   <li>Почта не должна быть пустой строкой, см. {@link IsEmpty}.</li>
     *   <li>Почта должна быть корректной, см. {@link IsNotEmail}.</li>
     * </ol>
     *
     * @return Список ошибок.
     */
    @Override
    public Collection<Failure> validate() {
      final var value = super.getValue();
      return new ValidationResultBuilder()
              .addIf(new IsEmpty(), value.isEmpty())
              .addIf(new IsNotEmail(), !EmailValidator.getInstance().isValid(value))
              .build();
    }

    /**
     * Электронная почта пользователя отсутствует.
     */
    public record IsEmpty() implements Failure {}

    /**
     * Переданное значение не является электронной почтой.
     */
    public record IsNotEmail() implements Failure {}
  }

  /**
   * <p>Пароль пользователя.</p>
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
   */
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class Password extends BaseAttribute<String> {
    /**
     * Проверяет входные данные на корректность и создаёт пароль пользователя.
     *
     * @param value Входные данные.
     * @return Ошибка или пароль пользователя.
     */
    public static Either<Collection<Failure>, Password> create(@NonNull String value) {
      final var password = new Password();
      password.setValue(value);

      final var failures = password.validate();
      return failures.isEmpty()
              ? Either.right(password)
              : Either.left(failures);
    }

    /**
     * Проверяет пароль пользователя на корректность.
     *
     * <p>Требования к паролю пользователя:</p>
     * <ol>
     *   <li>Пароль не должен быть короче 8 символов, см. {@link TooShort}.</li>
     *   <li>Пароль не должен быть длиннее 20 символов, см. {@link TooLong}.</li>
     *   <li>Пароль должен состоять только из символов латинского алфавита,
     *   цифр или знака подчёркивания, см. {@link InvalidCharacters}.</li>
     *   <li>Пароль должен содержать хотя бы один символ латинского алфавита,
     *   см. {@link DoesntHaveLatinCharacters}.</li>
     *   <li>Пароль должен содержать хотя одну цифру, см. {@link DoesntHaveNumbers}.</li>
     *   <li>Пароль должен содержать символы обоих регистров, см. {@link SameCharacterCase}.</li>
     * </ol>
     *
     * @return Список ошибок.
     */
    @Override
    public Collection<Failure> validate() {
      final var value = super.getValue();
      return new ValidationResultBuilder()
              .addIf(new TooShort(), value.length() < 8)
              .addIf(new TooLong(), value.length() > 20)
              .addIf(new InvalidCharacters(), !value.matches("\\w+"))
              .addIf(new DoesntHaveLatinCharacters(), !value.matches(".*[a-zA-Z]+.*"))
              .addIf(new DoesntHaveNumbers(), !value.matches(".*\\d+.*"))
              .addIf(
                      new SameCharacterCase(),
                      value.toLowerCase().equals(value) || value.toUpperCase().equals(value)
              )
              .build();
    }

    /**
     * Пароль пользователя слишком короткий.
     */
    public record TooShort() implements Failure {}

    /**
     * Пароль пользователя слишком длинный.
     */
    public record TooLong() implements Failure {}

    /**
     * Пароль пользователя содержит что-то кроме символов латинского алфавита,
     * цифр или знака подчёркивания.
     */
    public record InvalidCharacters() implements Failure {}

    /**
     * Пароль пользователя не содержит символы латинского алфавита.
     */
    public record DoesntHaveLatinCharacters() implements Failure {}

    /**
     * Пароль пользователя не содержит цифры.
     */
    public record DoesntHaveNumbers() implements Failure {}

    /**
     * Пароль пользователя не содержит символы обоих регистров.
     */
    public record SameCharacterCase() implements Failure {}
  }

  /**
   * Изображение пользователя.
   */
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class Image extends BaseAttribute<String> {
    /**
     * Проверяет входные данные на корректность и создаёт изображение пользователя.
     *
     * @param value Входные данные.
     * @return Ошибка или изображение пользователя.
     */
    public static Either<Collection<Failure>, Image> create(@NonNull String value) {
      final var image = new Image();
      image.setValue(value);

      final var failures = image.validate();
      return failures.isEmpty()
              ? Either.right(image)
              : Either.left(failures);
    }

    /**
     * Проверяет изображение пользователя на корректность.
     *
     * <p>Требования к изображению пользователя:</p>
     * <ol>
     *   <li>Изображение не должно быть пустой строкой, см. {@link IsEmpty}.</li>
     *   <li>Изображение должно быть строкой в формате, см. {@link IsNotBase64}.</li>
     * </ol>
     *
     * @return Список ошибок.
     */
    @Override
    public Collection<Failure> validate() {
      final var value = super.getValue();
      return new ValidationResultBuilder()
              .addIf(new IsEmpty(), value.isEmpty())
              .addIf(new IsNotBase64(), !Base64.isBase64(value))
              .build();
    }

    /**
     * Изображение пользователя отсутствует.
     */
    public record IsEmpty() implements Failure {}

    /**
     * Изображение пользователя не является строкой в формате Base-64.
     */
    public record IsNotBase64() implements Failure {}
  }

  /**
   * Роль пользователя.
   */
  public enum Role {
    /**
     * Администратор.
     */
    // TODO: Перенести строки в RoleResponse
    ADMIN(1, "Администратор"),
    /**
     * Менеджер.
     */
    MANAGER(2, "Менеджер"),
    /**
     * Контент-мейкер.
     */
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