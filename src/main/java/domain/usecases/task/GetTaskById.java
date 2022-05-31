package domain.usecases.task;

import domain.entity.errors.Failure;
import domain.entity.task.Task;
import domain.repositories.TaskRepositories;
import domain.usecases.UseCase;
import infrastructure.repositories.FakeTaskRepositories;
import io.vavr.control.Either;

import java.util.concurrent.Future;

/**
 * Получить задачу по Id
 */
public class GetTaskById implements UseCase<Integer, Task> {
    TaskRepositories repositories = new FakeTaskRepositories();
    @Override
    public Future<Either<Failure, Task>> execute(Integer id) {
        return repositories.get(id);
    }
}
