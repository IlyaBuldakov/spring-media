package ru.kiryanovid.domain.usecases.task;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.entity.task.Task;
import ru.kiryanovid.domain.repositories.TaskRepositories;
import ru.kiryanovid.domain.usecases.UseCase;
import io.vavr.control.Either;

import java.util.concurrent.Future;
@Component
@RequiredArgsConstructor
public class GetTaskById implements UseCase<Integer, Task> {
    @Autowired
    private final TaskRepositories repositories;
    @Override
    public Future<Either<Failure, Task>> execute(Integer id) {
        return null;
    }
}
