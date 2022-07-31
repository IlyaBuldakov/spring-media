package com.htc.domain.entities;

import com.htc.domain.entities.attributes.BaseAttribute;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.Collection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Задача.
 *
 * @param id Индентификатор задачи
 * @param name Название задачи.
 * @param contentType Тип контента.
 * @param description Описание задачи.
 * @param author Позьзователь - автор задачи.
 * @param executor Позьзователь - исполнитель задачи.
 * @param dateCreated Дата создания задачи.
 * @param dateExpired Дата окончания срока выполнения задачи.
 * @param status Статус задачи.
 */
public record Task(
        Id id,
        Task.Name name,
        Content.Type contentType,
        Description description,
        User author,
        User executor,
        LocalDateTime dateCreated,
        LocalDateTime dateExpired,
        Status status
) implements Entity {
  /**
   * Название задачи.
   */
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class Name extends BaseAttribute<String> {

    /**
     * Проверяет входные данные на корректность и создаёт название задачи.
     *
     * @param value Входные данные.
     * @return Название задачи или ошибка.
     */
    public static Either<Collection<Failure>, Task.Name> create(String value) {
      final var name = new Task.Name();
      name.setValue(value);

      final var failures = name.validate();
      return failures.isEmpty()
              ? Either.right(name)
              : Either.left(failures);
    }

    /**
     * Проверяет название задачи на корректность.
     *
     * <p>Требования к названию задачи:</p>
     * <ol>
     *   <li>Название не должно быть короче 3 символов, см. {@link TooShort}.</li>
     *   <li>Название не должно быть длиннее 255 символов, см. {@link TooLong}.</li>
     * </ol>
     *
     * @return Список ошибок.
     */
    @Override
    protected Collection<Failure> validate() {
      final var value = super.getValue();
      return new ValidationResultBuilder()
              .addIf(new TooShort(), value.length() < 3)
              .addIf(new TooLong(), value.length() > 255)
              .build();
    }


    /**
     * Имя пользователя слишком короткое.
     */
    public record TooShort() implements Failure {
    }

    /**
     * Имя пользователя слишком длинное.
     */
    public record TooLong() implements Failure {
    }
  }


  /**
   * Описание задачи.
   */
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class Description extends BaseAttribute<String> {

    /**
     * Проверяет входные данные на корректность и создаёт название задачи.
     *
     * @param value Входные данные.
     * @return Название задачи или ошибка.
     */
    public static Either<Collection<Failure>, Task.Description> create(String value) {
      final var description = new Task.Description();
      description.setValue(value);

      final var failures = description.validate();
      return failures.isEmpty()
              ? Either.right(description)
              : Either.left(failures);
    }

    /**
     * Проверяет описание задачи на корректность.
     *
     * <p>Требования к описанию задачи:</p>
     * <ol>
     *   <li>Название не должно быть короче 3 символов, см. {@link TooShort}.</li>
     *   <li>Название не должно быть длиннее 2048  символов, см. {@link TooLong}.</li>
     * </ol>
     *
     * @return Список ошибок.
     */
    @Override
    protected Collection<Failure> validate() {
      final var value = super.getValue();
      return new ValidationResultBuilder()
              .addIf(new TooShort(), value.length() < 3)
              .addIf(new TooLong(), value.length() > 2048)
              .build();
    }

    /**
     * Имя пользователя слишком короткое.
     */
    public record TooShort() implements Failure {
    }

    /**
     * Имя пользователя слишком длинное.
     */
    public record TooLong() implements Failure {
    }
  }

  /**
   * Статус задачи.
   */
  public enum Status {
    /**
     * В работе.
     */
    IN_WORK("В работе", 1),
    /**
     * Ожидает согласование.
     */
    FEEDBACK("Ожидает согласование", 2),
    /**
     * Выполнено.
     */
    APPROVED("Выполнено", 3);

    /**
     * Идентификатор статуса.
     *
     * @return id Идентификатор статуса.
     */
    @SuppressWarnings("JavadocDeclaration")
    private final @Getter int id;

    /**
     * Название статуса.
     *
     * @return Название статуса задачи.
     */
    @SuppressWarnings("JavadocDeclaration")
    private final @Getter String name;

    Status(String name, int id) {
      this.name = name;
      this.id = id;
    }
  }
}
