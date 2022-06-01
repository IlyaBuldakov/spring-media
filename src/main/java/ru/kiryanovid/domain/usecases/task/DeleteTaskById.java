package ru.kiryanovid.domain.usecases.task;

import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.usecases.UseCase;
import io.vavr.control.Either;

import java.util.concurrent.Future;

public class DeleteTaskById implements UseCase<Integer, Void> {
    @Override
    public Future<Either<Failure, Void>> execute(Integer param) {
        return null;
    }
}
