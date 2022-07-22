package com.htc.domain.usecases.contents;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.ContentsRepository;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * Реализация сценария удаления контента по идентификатору.
 */
@Component
@AllArgsConstructor
public class DeleteContentById {

    /**
     * Поле для внедрения реализации из infrastructure layer.
     */
    ContentsRepository contentsRepository;

    /**
     * Метод сценария.
     *
     * @param id Идентификатор контента
     * @return void.
     */
    public CompletableFuture<Either<Failure, Void>> execute(String id) {
        var expectedFailure = ValuesValidator.validateStringId(id);
        return expectedFailure == null ? contentsRepository.deleteById(Integer.parseInt(id))
                : CompletableFuture.completedFuture(Either.left(expectedFailure));
    }
}
