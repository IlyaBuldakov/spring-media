package com.htc.domain.entities.utility.parameters;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Параметр сущности.
 */
@ToString
@EqualsAndHashCode
public abstract class EntityParameter<ValueT> {
  /**
   * Значение параметра сущности.
   *
   * @return T Значение параметра сущности.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter ValueT value;

  protected EntityParameter(ValueT value) {
    this.value = value;
  }
}
