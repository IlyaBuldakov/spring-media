package com.htc.domain.usecases.comment;

import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValueParam;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.repositories.CommentRepository;
import com.htc.domain.repositories.TaskRepository;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utility.EitherHelper;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;

/**
 * Сценарий добавления нового комментария в задачу.
 */
@AllArgsConstructor
public final class AddComment implements UseCase<AddComment.Params, Comment> {
  /**
   * Параметры сценария добавления комментария.
   *
   * @param authorId идентификатор автора
   * @param message сообщение
   * @param taskId идентификатор задачи
   */
  public record Params(Id authorId,
                       String message,
                       Id taskId) {}

  private final CommentRepository repository;
  private final UserRepository userRepository;
  private final TaskRepository taskRepository;

  @Override
  public CompletableFuture<Either<Failure, Comment>> execute(Params params) {
    var failure = new InvalidValues();
    User user = null;
    try {
      user = userRepository.get(params.authorId()).get().get();
    } catch (InterruptedException | ExecutionException e) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_ID, "user not found");
    }
    var dateCreated = DateCreated.create();
    if (dateCreated.isLeft()) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_DATE_CREATED, "Date created");
    }
    Task task = null;
    try {
      task = taskRepository.get(params.taskId()).get().get();
    } catch (InterruptedException | ExecutionException e) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_ID, "task not found");
    }
    return failure.getValues().size() == 0
            ? repository.add(dateCreated.get(),
              user,
              params.message(),
              task)
            : EitherHelper.badLeft(failure);
  }
}
