package com.htc.domain.usecases.comment;

import com.htc.domain.entities.comments.Comment;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.tasks.Task;
import com.htc.domain.repositories.CommentRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;

/**
 * Сценарий получения комментария по ее идентификатору.
 */
@AllArgsConstructor
public final class GetAllCommentsByTask implements UseCase<Task, Collection<Comment>> {
  private final CommentRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Collection<Comment>>> execute(Task task) {
    return repository.getAllByTask(task);
  }
}


