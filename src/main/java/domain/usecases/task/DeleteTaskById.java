package domain.usecases.task;

import domain.entity.errors.Failure;
import domain.usecases.UseCase;
import io.vavr.control.Either;

import java.util.concurrent.Future;

public class DeleteTaskById implements UseCase<Integer, Void> {
    @Override
    public Future<Either<Failure, Void>> execute(Integer param) {
        return null;
    }
}
