package com.htc.domain.repositories;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.Content.Format;
import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;

import java.util.List;
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
     * @param name Имя контента.
     * @param type Тип контента.
     * @param format Формат контента.
     * @param taskId Идентификатор задачи.
     * @return void.
     */
    CompletableFuture<Either<Failure, Void>> create(String name, ContentType type, Format format, int taskId);

    /**
     * Удаление контента по идентификатору.
     *
     * @param id Идентификатор контента.
     * @return void.
     */
    CompletableFuture<Either<Failure, Void>> deleteById(int id);
}
