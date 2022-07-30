package com.htc.domain.usecases.contents;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.ContentsRepository;
import com.htc.infrastructure.repositories.UsersRepositoryImpl;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * Реализация сценария создания контента.
 */
@Component
@AllArgsConstructor
public class CreateContent {

    /**
     * Поле для внедрения реализации из infrastructure layer.
     */
    ContentsRepository contentsRepository;

    /**
     * Метод сценария.
     *
     * @param name   Имя контента.
     * @param type   Тип контента.
     * @param format Формат контента.
     * @param taskId Идентификатор задачи.
     * @return void.
     */
    public CompletableFuture<Either<Failure, Void>> execute(int authorId, String fileName, File file, String url, String taskId) {
        var expectedFailure = ValuesValidator.validateStringId(taskId);
        return expectedFailure == null ? contentsRepository.create(name, type, format, Integer.parseInt(taskId))
                : CompletableFuture.completedFuture(Either.left(expectedFailure));
    }
}
