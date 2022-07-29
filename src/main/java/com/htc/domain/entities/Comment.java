package com.htc.domain.entities;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.Collection;
import lombok.NonNull;

/**
 * Комментарий.
 *
 * @param id Идентификатор комментария.
 * @param dateCreated Дата создания комментария.
 * @param author Пользователь - автора комментария.
 * @param task Задача в которой оставлен комментарий.
 * @param message Текст комментария.
 */
public record Comment(Id id, LocalDateTime dateCreated, User author, Task task, Message message)
        implements Entity {

  /**
   * Текст комментария.
   */
  public static final class Message extends BaseAttribute<String> {
    /**
     * Создаёт сообщение уведомления.
     *
     * @param value Входные данные.
     * @return Сообщение уведомления.
     */
    public static Either<Collection<Failure>, Message> create(@NonNull String value) {
      final var message = new Message();
      message.setValue(value);

      final var failures = message.validate();
      return failures.isEmpty()
              ? Either.right(message)
              : Either.left(failures);
    }

    /**
     * Проверяет текст комментария на корректность.
     *
     * <p>Требования к тексту комментария:</p>
     * <ol>
     *   <li>Имя не должно быть короче 10 символов, см. {@link TooShort}.</li>
     *   <li>Имя не должно быть длиннее 2000 символов, см. {@link TooLong}.</li>
     * </ol>
     *
     * @return Список ошибок.
     */
    @Override
    public Collection<Failure> validate() {
      final var value = super.getValue();
      return new ValidationResultBuilder()
              .addIf(new TooShort(), value.length() < 10)
              .addIf(new TooLong(), value.length() > 2000)
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
}
