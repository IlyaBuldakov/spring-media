package domain.usecases.task;

import domain.entity.errors.Failure;
import domain.entity.task.Task;
import domain.usecases.UseCase;
import io.vavr.control.Either;

import java.util.concurrent.Future;

public class CreateTask implements UseCase<Task, Task> {
    @Override
    public Future<Either<Failure, Task>> execute(Task task) {
        return null;
    }
}
