package com.htc.domain.usecases.task;

import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.Type;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValueParam;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.file.File;
import com.htc.domain.entities.task.Status;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.EntityName;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.repositories.CommentRepository;
import com.htc.domain.repositories.ContentRepository;
import com.htc.domain.repositories.FileRepository;
import com.htc.domain.repositories.TaskRepository;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utility.EitherHelper;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;

/**
 * Сценарий изменения задачи.
 */
@AllArgsConstructor
public final class UpdateTaskById implements UseCase<UpdateTaskById.Params, Task> {
  /**
   * Параметры сценария обновления задачи по идентификатору.
   *
   * @param id идентификатор задачи
   * @param name наименование
   * @param type тип
   * @param description описание
   * @param fileId идентификатор файла
   * @param authorId идентификатор автора
   * @param executorId идентификатор исполнителя
   * @param dateCreated дата создания
   * @param dateExpired дата выполнения
   * @param contentId идентификатор контента
   * @param commentId идентификатор комментария
   * @param status статус
   */
  public record Params(Id id, EntityName name, Type type, String description, Id fileId,
                       Id authorId, Id executorId, DateCreated dateCreated, DateCreated dateExpired,
                       Id contentId, Id commentId, Status status) {}

  private final TaskRepository repository;
  private final UserRepository userRepository;
  private final FileRepository fileRepository;
  private final ContentRepository contentRepository;
  private final CommentRepository commentRepository;

  @Override
  public CompletableFuture<Either<Failure, Task>> execute(Params params) {
    var failure = new InvalidValues();
    File file = null;
    try {
      file = fileRepository.get(params.fileId()).get().get();
    } catch (InterruptedException | ExecutionException e) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_ID, "file not found");
    }
    User author = null;
    try {
      author = userRepository.get(params.authorId()).get().get();
    } catch (InterruptedException | ExecutionException e) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_ID, "user not found (author)");
    }
    User executor = null;
    try {
      executor = userRepository.get(params.executorId()).get().get();
    } catch (InterruptedException | ExecutionException e) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_ID, "user not found (executor)");
    }
    Content content = null;
    try {
      content = contentRepository.get(params.contentId()).get().get();
    } catch (InterruptedException | ExecutionException e) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_ID, "content not found");
    }
    Comment comment = null;
    try {
      comment = commentRepository.get(params.commentId()).get().get();
    } catch (InterruptedException | ExecutionException e) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_ID, "comment not found");
    }
    //TODO description - тип данных / проверка на null и empty
    return failure.getValues().size() == 0
            ? repository.update(params.id(), params.name(), params.type(),
            params.description(), file, author, executor, params.dateCreated(),
            params.dateExpired(), content, comment, params.status())
            : EitherHelper.badLeft(failure);
  }
}
