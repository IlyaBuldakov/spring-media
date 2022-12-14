package com.htc.domain.repositories;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.Content.ContentFormat;
import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.failure.Failure;
import io.vavr.control.Either;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий контента.
 */
public interface ContentsRepository {

  /**
   * Получение списка контента.
   *
   * @return Список контента.
   */
  CompletableFuture<Either<Failure, List<Content>>> getAll();

  /**
   * Создание контента.
   *
   * @param name          Имя контента.
   * @param type          Тип контента.
   * @param contentFormat Формат контента.
   * @param taskId        Идентификатор задачи.
   * @return void.
   */
  CompletableFuture<Either<Failure, Void>> create(int id, String name,
                                                  ContentType type,
                                                  ContentFormat contentFormat,
                                                  String url, int taskId);

  /**
   * Удаление контента по идентификатору.
   *
   * @param id Идентификатор контента.
   * @return void.
   */
  CompletableFuture<Either<Failure, Void>> deleteById(int id);

  /**
   * Получение множества URL контента по идентификатору
   * задачи, которые относятся к этой задаче.
   *
   * @param taskId Идентификатор задачи.
   * @return Множество URL контента.
   */
  Set<String> findRelevantToTaskContentUrl(int taskId);
}
