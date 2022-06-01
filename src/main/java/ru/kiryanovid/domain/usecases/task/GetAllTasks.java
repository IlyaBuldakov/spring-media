package ru.kiryanovid.domain.usecases.task;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.entity.task.Task;
import ru.kiryanovid.domain.repositories.TaskRepositories;
import ru.kiryanovid.domain.usecases.UseCase;
import io.vavr.control.Either;

import java.util.concurrent.Future;

/**
 *  Получить список всех задач
 */
@Component
@AllArgsConstructor
public final class GetAllTasks implements UseCase<Void, Iterable<Task>> {
    private final TaskRepositories repositories;

    @Override
    public Future<Either<Failure, Iterable<Task>>> execute(Void param) {
        return repositories.getAll();
    }
}
