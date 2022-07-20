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
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.entities.utility.parameters.file.FileName;
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
//@Component
@AllArgsConstructor
public final class UpdateTaskById implements UseCase<UpdateTaskById.Params, Task> {
  /**
   * Параметры сценария обновления задачи по идентификатору.
   */
  public record Params(Long id, String idKey,
                       String name, String nameKey,
                       Type type, String typeKey,
                       String description, String descriptionKey,
                       Long fileId, String fileKey,
                       Long authorId, String authorKey,
                       Long executorId, String executorKey,
                       String dateCreated, String dateCreatedKey,
                       String dateExpired, String dateExpiredKey,
                       Long contentId, String contentKey,
                       Long commentId, String commentKey,
                       Status status, String statusKey) {}

  private final TaskRepository repository;
  private final UserRepository userRepository;
  private final FileRepository fileRepository;
  private final ContentRepository contentRepository;
  private final CommentRepository commentRepository;

  @Override
  public CompletableFuture<Either<Failure, Task>> execute(Params params) {
    var failure = new InvalidValues();
    var id = Id.create(params.id());
    if (id.isLeft()) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_ID, params.idKey);
    }
    var name = FileName.create(params.name());
    if (name.isLeft()) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_NAME, params.nameKey);
    }
    File file = null;
    try {
      file = fileRepository.get(Id.create(params.fileId()).get()).get().get();
    } catch (InterruptedException | ExecutionException e) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_ID, "file not found");
    }
    User author = null;
    try {
      author = userRepository.get(Id.create(params.authorId()).get()).get().get();
    } catch (InterruptedException | ExecutionException e) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_ID, "user not found (author)");
    }
    User executor = null;
    try {
      executor = userRepository.get(Id.create(params.executorId()).get()).get().get();
    } catch (InterruptedException | ExecutionException e) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_ID, "user not found (executor)");
    }
    var dateCreated = DateCreated.create(params.dateCreated());
    if (dateCreated.isLeft()) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_DATE_CREATED, "Date created");
    }
    var dateExpired = DateCreated.create(params.dateExpired());
    if (dateExpired.isLeft()) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_DATE_CREATED, "Date expired");
    }
    Content content = null;
    try {
      content = contentRepository.get(Id.create(params.contentId()).get()).get().get();
    } catch (InterruptedException | ExecutionException e) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_ID, "content not found");
    }
    Comment comment = null;
    try {
      comment = commentRepository.get(Id.create(params.commentId()).get()).get().get();
    } catch (InterruptedException | ExecutionException e) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_ID, "comment not found");
    }
    //TODO description - тип данных / проверка на null и empty
    return failure.getValues().size() == 0
            ? repository.update(id.get(), name.get(), params.type(),
            params.description(), file, author, executor, dateCreated.get(),
            dateExpired.get(), content, comment, params.status())
            : EitherHelper.badLeft(failure);
  }
}
