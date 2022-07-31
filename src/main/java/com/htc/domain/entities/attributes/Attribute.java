package com.htc.domain.entities.attributes;

/**
 * Базовая реализация параметра сущности.
 *
 * @param <ValueT> Тип данных значения параметра сущности.
 */
public interface Attribute<ValueT> {
  /**
   * Возвращает значение параметра сущности.
   *
   * @return Значение параметра сущности.
   */
  ValueT getValue();
}
