package com.htc.domain.usecases.task;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.TasksRepository;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@AllArgsConstructor
public class DeleteTask {

    TasksRepository tasksRepository;

    public CompletableFuture<Either<Failure, Void>> execute(String id) {
        var expectedFailure = ValuesValidator.validateStringId(id);
        if (expectedFailure != null) {
            return CompletableFuture.completedFuture(Either.left(expectedFailure));
        }
        tasksRepository.deleteById(Integer.parseInt(id));
        return null;
    }
}
