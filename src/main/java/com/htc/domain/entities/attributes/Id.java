package com.htc.domain.entities.attributes;

import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.util.Collection;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Идентификатор сущности.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Id extends BaseAttribute<Integer> {
  /**
   * Проверяет входные данные на корректность и создаёт идентификатор сущности.
   *
   * @param value Входные данные.
   * @return Ошибка или идентификатор сущности.
   */
  public static @NonNull Either<Collection<Failure>, Id> create(@NonNull Integer value) {
    final var id = new Id();
    id.setValue(value);

    final var failures = id.validate();
    return failures.isEmpty()
        ? Either.right(id)
        : Either.left(failures);
  }

  /**
   * Проверяет идентификатор сущности на корректность.
   *
   * <p>Требования к идентификатору сущности:</p>
   * <ol>
   *   <li>Идентификатор не должен быть меньше единицы, см. {@link LessThanOne}.</li>
   * </ol>
   *
   * @return Список ошибок.
   */
  @Override
  @NonNull
  public Collection<Failure> validate() {
    final var value = super.getValue();
    return new ValidationResultBuilder()
        .addIf(new LessThanOne(), value <= 0)
        .build();
  }

  /**
   * Идентификатор меньше единицы.
   */
  public record LessThanOne() implements Failure {}
}
