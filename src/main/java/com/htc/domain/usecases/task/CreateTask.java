package com.htc.domain.usecases.task;

import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.task.Task;
import com.htc.domain.repositories.TasksRepository;
import com.htc.domain.repositories.UsersRepository;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

/**
 * Реализация сценария создания задачи.
 */
@AllArgsConstructor
@Component
public class CreateTask {

    /**
     * Поле для внедрения реализации из infrastructure layer.
     */
    TasksRepository tasksRepository;

    /**
     * Поле для внедрения реализации из infrastructure layer.
     */
    UsersRepository usersRepository;

    /**
     * Метод сценария.
     *
     * @param name Имя задачи.
     * @param type Тип контента задачи.
     * @param description Описание задачи.
     * @param author Автор задачи.
     * @param executor Исполнитель задачи.
     * @param dateExpired Дата выполнения задачи (срок выполнения).
     * @return void.
     */
    public CompletableFuture<Either<Failure, Void>> execute(String name, ContentType type,
                                                            String description, String author,
                                                            String executor, LocalDate dateExpired) {
        var expectedFailure = ValuesValidator.checkTaskFields(name, description, author, executor);
        if (expectedFailure != null) {
            return CompletableFuture.completedFuture(Either.left(expectedFailure));
        }
        tasksRepository.create(name, type, description, Integer.parseInt(author), Integer.parseInt(executor), dateExpired);
        return CompletableFuture.completedFuture(Either.right(null));
    }
}
