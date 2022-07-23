package com.htc.domain.usecases.content;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.ContentRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.time.LocalDate;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий получения медиакотента по запросу.
 */
@Component
@AllArgsConstructor
public final class GetContentByQuery
        implements UseCase<GetContentByQuery.Params, Collection<Content>> {
  private final ContentRepository repository;

  /**
   * Параметры для выполнения сценария.
   *
   * @param page Номер страницы летны медиакотента.
   * @param count Число элементов на станице летны медиакотента.
   * @param author Автор медиакотента.
   * @param date Дата публикации медиакотента.
   * @param typeId Тип медиакотента.
   */
  public record Params(
          Integer page,
          Integer count,
          String author,
          LocalDate date,
          Integer typeId) {
  }

  @Override
  public CompletableFuture<Either<Failure, Collection<Content>>> execute(
          GetContentByQuery.Params param) {
    return repository.findByQuery(
            param.page,
            param.count,
            param.author,
            param.date,
            param.typeId);
  }
}
