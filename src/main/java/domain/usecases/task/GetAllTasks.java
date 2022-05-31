package domain.usecases.task;

import domain.entity.errors.Failure;
import domain.entity.task.Task;
import domain.repositories.TaskRepositories;
import domain.usecases.UseCase;
import infrastructure.repositories.FakeTaskRepositories;
import io.vavr.control.Either;

import java.util.concurrent.Future;

/**
 * Получить список всех задач
 */
public class GetAllTasks implements UseCase<Void, Iterable<Task>> {
    TaskRepositories repositories = new FakeTaskRepositories();

    @Override
    public Future<Either<Failure, Iterable<Task>>> execute(Void param) {
        return repositories.getAll();
    }
}
