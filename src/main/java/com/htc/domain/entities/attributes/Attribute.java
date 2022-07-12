package com.htc.domain.entities.attributes;

import java.util.Objects;

/**
 * Параметр сущности.
 *
 * @param <ValueT> Тип данных значения параметра сущности.
 */
public abstract class Attribute<ValueT> {
  /**
   * Значение параметра сущности.
   */
  private final ValueT value;

  /**
   * Значение параметра сущности.
   *
   * @return Значение параметра сущности.
   */
  public ValueT getValue() {
    return value;
  }

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
