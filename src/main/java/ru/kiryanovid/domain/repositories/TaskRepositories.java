package ru.kiryanovid.domain.repositories;

import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.entity.task.Task;
import io.vavr.control.Either;

import java.util.concurrent.Future;

/**
 * Репозиторий задач
 */

public interface TaskRepositories {
    /**
     * Создаёт задачу.
     *
     * @param task Задача.
     */
    Future<Either<Failure, Task>> create(Task task);

    /**
     * Обновляет задачу.
     *
     * @param task задача.
     */
    Future<Either<Failure, Task>> update(Task task);

    /**
     * Удаляет задачу.
     *
     * @param id Идентификатор задачу.
     */
    Future<Either<Failure, Void>> delete(int id);

    /**
     * Получает пользователя.
     *
     * @param id Идентификатор задачи.
     * @return задача.
     */
    Future<Either<Failure, Task>> get(int id);

    /**
     * Получает список всех задач.
     *
     * @return Список задач.
     */
    Future<Either<Failure, Iterable<Task>>> getAll();
}
