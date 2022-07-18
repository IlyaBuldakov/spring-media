package ru.kiryanovid.domain.usecases.task;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.entity.task.Task;
import ru.kiryanovid.domain.repositories.TaskRepositories;
import ru.kiryanovid.domain.usecases.UseCase;

import java.util.concurrent.CompletableFuture;
@Component
@RequiredArgsConstructor
public final class GetTaskById implements UseCase<Integer, Task> {
    @Autowired
    private final TaskRepositories repositories;
    @Override
    public CompletableFuture<Either<Failure, Task>> execute(Integer id) {
        return repositories.get(id);
    }
}
