package com.htc.domain.entities;

import com.htc.domain.entities.attributes.BaseAttribute;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.Collection;
import lombok.NonNull;

/**
 * Уведомление.
 *
 * @param id Индентификатор уведомления.
 * @param type Тип уведомления.
 * @param dateCreated Дата получения уведомления.
 * @param message Сообщение уведомления.
 * @param user Пользователь - получатель уведомления.
 * @param task Задача связанная с уведомлнием
 */
public record Notification(
        Id id,
        Type type,
        LocalDateTime dateCreated,
        Message message,
        User user,
        Task task) implements Entity {


  /**
   * Тип уведомления.
   */
  public enum Type {
    VIDEO,
    AUDIO,
    PHOTO,
    COMMENT,
    CONTENT
  }

  /**
   * Сообщение уведомления.
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
}
