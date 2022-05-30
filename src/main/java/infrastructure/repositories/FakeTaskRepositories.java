package infrastructure.repositories;

import domain.entity.errors.Failure;
import domain.entity.task.Task;
import domain.repositories.TaskRepositories;
import io.vavr.control.Either;

import java.util.concurrent.Future;

/**
 * Реализация репозитория задач с ненастоящими данными.
 */
public class FakeTaskRepositories implements TaskRepositories {
    @Override
    public Future<Either<Failure, Task>> create(Task task) {
        return null;
    }

    @Override
    public Future<Either<Failure, Task>> update(Task task) {
        return null;
    }

    @Override
    public Future<Either<Failure, Void>> delete(int id) {
        return null;
    }

    @Override
    public Future<Either<Failure, Task>> get(int id) {
        return null;
    }

    @Override
    public Future<Either<Failure, Iterable<Task>>> getAll() {
        return null;
    }
}
