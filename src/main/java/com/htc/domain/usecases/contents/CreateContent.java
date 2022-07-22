package com.htc.domain.usecases.contents;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.ContentsRepository;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@AllArgsConstructor
public class CreateContent {

    ContentsRepository contentsRepository;

    public CompletableFuture<Either<Failure, Void>> execute(String name, ContentType type, Content.Format format, String taskId) {
        var expectedFailure = ValuesValidator.validateStringId(taskId);
        if (expectedFailure != null) {
            return CompletableFuture.completedFuture(Either.left(expectedFailure));
        }
        contentsRepository.create(name, type, format, Integer.parseInt(taskId));
        return CompletableFuture.completedFuture(Either.right(null));
    }
}
