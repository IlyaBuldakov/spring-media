package com.htc.domain.usecases.task;

import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.TasksRepository;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

@Component
@AllArgsConstructor
public class UpdateTask {

    TasksRepository tasksRepository;

    public CompletableFuture<Either<Failure, Void>> execute(String id, String name, ContentType type, String description,
                                                            String author, String executor, LocalDate dateExpired) {
        var expectedFailure = ValuesValidator.checkTaskFields(id, name, description, author, executor);
        if (expectedFailure != null) {
            return CompletableFuture.completedFuture(Either.left(expectedFailure));
        }
        tasksRepository.update(Integer.parseInt(id), name, type, description,
                Integer.parseInt(author), Integer.parseInt(executor), dateExpired);
        return CompletableFuture.completedFuture(Either.right(null));
    }
}
