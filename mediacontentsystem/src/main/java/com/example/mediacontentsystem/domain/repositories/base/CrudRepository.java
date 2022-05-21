package com.example.mediacontentsystem.domain.repositories.base;

import com.example.mediacontentsystem.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.util.List;
import java.util.concurrent.Future;

/**
 * CRUD Интерфейс.
 *
 * @param <EntityT> Тип сущности.
 */
public interface CrudRepository<EntityT> {
  /**
   * Создает сущность.
   *
   * @param entity сущность.
   */
  Future<Either<Failure, EntityT>> create(EntityT entity);

  /**
   * Получает сущность по идентификатору.
   *
   * @param id идентификатор сущности.
   * @return Получает сущность по идентификатору.
   */
  Future<Either<Failure, EntityT>> get(int id);

  /**
   * Получает список всех сущностей.
   *
   * @return Получает список всех сущностей.
   */
  Future<Either<Failure, Iterable<EntityT>>> getAll();

  /**
   * Обновляет сущность.
   *
   * @param entity сущность.
   */
  Future<Either<Failure, EntityT>> update(EntityT entity);

  /**
   * Удаляет сущность.
   *
   * @param id идентификатор сущности.
   */
  Future<Either<Failure, Void>> delete(int id);
}
