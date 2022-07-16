package com.htc.domain.usecases.comment;

import com.htc.domain.entities.Comment;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.CommentRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

// TODO Реализовать механизм получения комментариев по id задачи
//  – сейчас все существующие комментарии.
// TODO Реализовать сортировку комментариев по дате создания.
/**
 * Сценарий получения комментариев по идентификатору задачи.
 */
@Component
@AllArgsConstructor
public class GetCommentsByTaskId implements
    UseCase<GetCommentsByTaskId.Params,
    Collection<Comment>> {
  /**
   * Параметры сценария получения комментариев по идентификатору задачи.
   *
   * @param taskId Идентификатор задачи.
   * @param key Ключ идентификатора задачи.
   */
  public record Params(int taskId, String key) {}

  private final CommentRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Collection<Comment>>> execute(Params params) {
    return repository.getCommentsByTaskId(params.taskId);
  }
}
