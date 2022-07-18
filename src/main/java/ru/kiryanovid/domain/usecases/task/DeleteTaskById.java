package ru.kiryanovid.domain.usecases.task;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.repositories.TaskRepositories;
import ru.kiryanovid.domain.usecases.UseCase;
import io.vavr.control.Either;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
@Component
@RequiredArgsConstructor
public final class DeleteTaskById implements UseCase<Integer, Void> {
    @Autowired
    private final TaskRepositories repositories;
    @Override
    public CompletableFuture<Either<Failure, Void>> execute(Integer param) {
        repositories.delete(param);
        return null;
    }
}
