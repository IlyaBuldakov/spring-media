package com.htc.domain.usecases.content;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.repositories.ContentRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utility.Results;
import io.vavr.control.Either;
import java.io.File;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий удаления контента по его идентификатору.
 */
@Component
@AllArgsConstructor
public final class DeleteContentById implements UseCase<DeleteContentById.Params, Void> {
  /**
   * Параметры сценария получения контента по его идентификатору.
   *
   * @param id Идентификатор контента.
   * @param key Ключ идентификатора пользователя.
   */
  public record Params(int id, String key) {}

  private final ContentRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Void>> execute(Params params) {
    var id = Id.create(params.id);
    if (id.isRight()) {
      repository.get(id.get()).thenApply(content -> {
        var urlContent = content.get().getUrlContent();
        var fileName = content.get().getName();
        File file = new File(urlContent + "/" + fileName);
        return file.delete();
      });
      return repository.delete(id.get().getValue());
    }

    var failure = new InvalidValues();
    failure.invalidValues().put(id.getLeft(), params.key);
    return Results.fail(failure);
  }
}
