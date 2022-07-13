package com.htc.domain.entities.attributes;

import java.util.Objects;
import lombok.Getter;

/**
 * Параметр сущности.
 *
 * @param <ValueT> Тип данных значения параметра сущности.
 */
public abstract class Attribute<ValueT> {
  /**
   * Значение параметра сущности.
   *
   * @return T Значение параметра сущности.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter ValueT value;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Attribute<?> that)) {
      return false;
    }

    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return value.toString();
  }

  protected Attribute(ValueT value) {
    this.value = value;
  }
}
