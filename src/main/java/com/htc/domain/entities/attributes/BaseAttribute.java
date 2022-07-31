package com.htc.domain.entities.attributes;

import com.htc.domain.entities.attributes.Attribute;
import com.htc.domain.entities.failures.Failure;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * Базовая реализация параметра сущности.
 *
 * @param <ValueT> Тип данных значения параметра сущности.
 */
@ToString
@EqualsAndHashCode
public abstract class BaseAttribute<ValueT> implements Attribute<ValueT> {
  /**
   * Значение параметра сущности.
   *
   * @param value Новое значение параметра сущности.
   * @return Значение параметра сущности.
   */
  @SuppressWarnings("JavadocDeclaration")
  @Getter
  @Setter(AccessLevel.PROTECTED)
  private ValueT value;

  /**
   * Проверяет значение параметра сущности на корректность.
   *
   * @return Список ошибок.
   */
  protected abstract Collection<Failure> validate();

  public final class ValidationResultBuilder {
    private final Collection<Failure> failures = new ArrayList<>();

    public ValidationResultBuilder addIf(
            @NonNull Failure failure,
            boolean condition) {
      if (condition) {
        failures.add(failure);
      }

      return this;
    }

    public ValidationResultBuilder addIf(
            @NonNull Failure failure,
            @NonNull Supplier<Boolean> conditionSupplier) {
      return addIf(failure, conditionSupplier.get());
    }

    public Collection<Failure> build() {
      return this.failures;
    }
  }
}
