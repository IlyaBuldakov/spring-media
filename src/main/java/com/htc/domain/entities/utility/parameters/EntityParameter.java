package com.htc.domain.entities.utility.parameters;

import java.util.Objects;
import lombok.Getter;

/**
 * Параметр сущности.
 */
public abstract class EntityParameter<ValueT> {
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

    if (!(o instanceof EntityParameter<?>)) {
      return false;
    }
    var that = (EntityParameter<?>) o;
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

  protected EntityParameter(ValueT value) {
    this.value = value;
  }
}
