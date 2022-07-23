package com.htc.infrastructure.repositories;

import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.entities.task.Task;
import com.htc.domain.repositories.TasksRepository;
import com.htc.infrastructure.mappers.TaskMapper;
import com.htc.infrastructure.jpa.TasksJpaRepository;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Реализация репозитория для работы с задачами.
 */
@Component
@AllArgsConstructor
public class TasksRepositoryImpl implements TasksRepository {

    /**
     * JPA-репозиторий.
     */
    TasksJpaRepository tasksJpaRepository;

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
    @Override
    public CompletableFuture<Either<Failure, Void>> create(String name, ContentType type, String description, int author, int executor, LocalDate dateExpired) {
        tasksJpaRepository.save(new TaskMapper(name, type, description, author, executor, dateExpired));
        return CompletableFuture.completedFuture(Either.right(null));
    }

    /**
     * Получение задачи по идентификатору.
     *
     * @param id Идентификатор задачи.
     * @return Задача.
     */
    @Override
    public CompletableFuture<Either<Failure, Task>> getById(int id) {
        var task = tasksJpaRepository.findById(id);
        if (task.isPresent()) {
            return CompletableFuture.completedFuture(Either.right(task.get()));
        }
        return CompletableFuture.completedFuture(Either.left(NotFound.TASK));
    }

    /**
     * Получение списка всех задач.
     *
     * @return Список задач.
     */
    @Override
    public CompletableFuture<Either<Failure, List<Task>>> getAll() {
        return CompletableFuture.completedFuture(Either.right(
                new ArrayList<>(tasksJpaRepository.findAll())
        ));
    }

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
    @Override
    public CompletableFuture<Either<Failure, Void>> update(int id, String name, ContentType type, String description, int author, int executor, LocalDate dateExpired) {
        tasksJpaRepository.save(new TaskMapper(id, name, type, description, author, executor, dateExpired));
        return CompletableFuture.completedFuture(Either.right(null));
    }

    /**
     * Удаление задачи по идентификатору.
     *
     * @param id Идентификатор задачи.
     * @return void.
     */
    @Override
    public CompletableFuture<Either<Failure, Void>> deleteById(int id) {
        try {
            tasksJpaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            return CompletableFuture.completedFuture(Either.left(NotFound.TASK));
        }
        return CompletableFuture.completedFuture(Either.right(null));
    }
}
