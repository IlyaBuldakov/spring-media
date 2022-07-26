package com.htc.domain.entities;

import com.htc.domain.entities.attributes.Id;

/**
 * Сущность доменного слоя.
 */
public interface Entity {
  /**
   * Возвращает идентификатор сущности доменного слоя.
   *
   * @return Идентификатор сущности доменного слоя.
   */
  Id id();

  /**
   * Модель сущности доменного слоя.
   *
   * @param <E> Сущность доменного слоя.
   */
  interface Model<E extends Entity> {
    E toEntity();
  }

  /**
   * Представление сущности доменного слоя.
   *
   * @param <V> Представление сущности доменного слоя.
   * @param <E> Сущность доменного слоя.
   */
  interface View<V extends View<V, E>, E extends Entity> {
    V fromEntity(E entity);
  }
}