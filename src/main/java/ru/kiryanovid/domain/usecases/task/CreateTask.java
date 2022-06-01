package ru.kiryanovid.domain.usecases.task;

import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.entity.task.Task;
import ru.kiryanovid.domain.usecases.UseCase;
import io.vavr.control.Either;

import java.util.concurrent.Future;

public class CreateTask implements UseCase<Task, Task> {
    @Override
    public Future<Either<Failure, Task>> execute(Task task) {
        return null;
    }
}
