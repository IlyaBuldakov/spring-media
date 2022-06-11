package com.htc.application.dtos;

/**
 * Общее представление сущностей.
 *
 * @param <EntityT> тип сущности
 */
public abstract class DataTransferObject<EntityT> {
  public DataTransferObject(EntityT entity) {}
}
