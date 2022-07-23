package com.htc.domain.usecases.content;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.repositories.ContentRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utility.Results;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий загрузки контента.
 */
@Component
@AllArgsConstructor
public final class UploadContent implements UseCase<UploadContent.Params, Content> {
  /**
   * Параметры сценария загрузки контента.
   *
   * @param type Тип контента.
   * @param name Название контента.
   * @param dateCreated Дата создания контента.
   * @param authorId Идентификатор автора контента.
   * @param format Формат контента.
   * @param urlContent Путь к контенту.
   * @param urlPreview Путь к превью контента.
   * @param taskId Идентификатор задачи содержащей контент.
   */
  public record Params(
      Content.Type type, String typeKey,
      String name, String nameKey,
      LocalDateTime dateCreated, String dateCreatedKey,
      int authorId, String authorIdKey,
      Content.Format format, String formatKey,
      String urlContent, String urlContentKey,
      String urlPreview, String urlPreviewKey,
      int taskId, String taskIdKey) {}

  private final ContentRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Content>> execute(Params params) {
    var failure = new InvalidValues();

    return failure.invalidValues().size() == 0
        ? repository.upload(
        params.type,
        params.name,
        params.dateCreated,
        params.authorId,
        params.format,
        params.urlContent,
        params.urlPreview,
        params.taskId)
        : Results.fail(failure);
  }
}