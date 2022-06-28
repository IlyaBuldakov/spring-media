package ru.kiryanovid.domain.usecases.task;

import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.entity.task.Task;
import ru.kiryanovid.domain.repositories.TaskRepositories;
import ru.kiryanovid.domain.usecases.UseCase;

import java.util.concurrent.CompletableFuture;

/**
 *  Получить список всех задач
 */
@Component
@AllArgsConstructor
public final class GetAllTasks implements UseCase<Void, Iterable<Task>> {
    @Autowired
    private final TaskRepositories repositories;

    @Override
    public CompletableFuture<Either<Failure, Iterable<Task>>> execute(Void param) {
        return repositories.getAll();
    }
}
