package com.htc.domain.repositories;

import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.task.Task;
import io.vavr.control.Either;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий задач.
 */
public interface TasksRepository {

    /**
     * Создание задачи.
     *
     * @param name Имя задачи.
     * @param type Тип задачи.
     * @param description Описание задачи.
     * @param author Автор задачи.
     * @param executor Исполнитель задачи.
     * @param dateExpired Дата выполнения задачи (срок выполнения).
     * @return void.
     */
    CompletableFuture<Either<Failure, Void>> create(String name,
                                                    ContentType type,
                                                    String description,
                                                    int author,
                                                    int executor,
                                                    LocalDate dateExpired);

    /**
     * Получение задачи по идентификатору.
     *
     * @param id Идентификатор задачи.
     * @return Задача.
     */
    CompletableFuture<Either<Failure, Task>> getById(int id);

    /**
     * Получение списка задач.
     *
     * @return Список задач.
     */
    CompletableFuture<Either<Failure, List<Task>>> getAll();

    /**
     * Обновление задачи.
     *
     * @param id Идентификатор задачи.
     * @param name Имя задачи.
     * @param type Тип задачи.
     * @param description Описание задачи.
     * @param author Автор задачи.
     * @param executor Исполнитель задачи
     * @param dateExpired Дата выполнения задачи (срок выполнения).
     * @return void.
     */
    CompletableFuture<Either<Failure, Void>> update(int id,
                                                    String name,
                                                    ContentType type,
                                                    String description,
                                                    int author,
                                                    int executor,
                                                    LocalDate dateExpired);

    /**
     * Удаление задачи по идентификатору.
     *
     * @param id Идентификатор задачи.
     * @return void.
     */
    CompletableFuture<Either<Failure, Void>> deleteById(int id);
}
