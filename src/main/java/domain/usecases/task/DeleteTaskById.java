package domain.usecases.task;

import domain.entity.errors.Failure;
import domain.repositories.TaskRepositories;
import domain.usecases.UseCase;
import infrastructure.repositories.FakeTaskRepositories;
import io.vavr.control.Either;

import java.util.concurrent.Future;

public class DeleteTaskById implements UseCase<Integer, Void> {
    TaskRepositories repositories = new FakeTaskRepositories();
    @Override
    public Future<Either<Failure, Void>> execute(Integer id) {
        return repositories.delete(id);
    }
}
