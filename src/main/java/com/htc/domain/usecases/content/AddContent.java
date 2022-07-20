package com.htc.domain.usecases.content;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValueParam;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.file.File;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.repositories.ContentRepository;
import com.htc.domain.repositories.FileRepository;
import com.htc.domain.repositories.TaskRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utility.EitherHelper;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий добавления нового контента.
 */
@Component
@AllArgsConstructor
public final class AddContent implements UseCase<AddContent.Params, Content> {
  /**
   * Параметры сценария добавления контента.
   *
   * @param taskId идентификатор задачи, относящийся к контенту
   * @param taskKey ключ идентификатора задачи, относящейся к контенту
   * @param fileId идентификатор файла, относящийся к контенту
   * @param fileKey ключ идентификатора файла, относящийся к контенту
   */
  public record Params(Long taskId, String taskKey,
                       Long fileId, String fileKey) {}

  private final ContentRepository repository;
  private final FileRepository fileRepository;
  private final TaskRepository taskRepository;

  @Override
  public CompletableFuture<Either<Failure, Content>> execute(Params params) {
    var failure = new InvalidValues();
    //TODO возможно filename переименовать в EntityName
    Task task = null;
    try {
      task = taskRepository.get(Id.create(params.taskId()).get()).get().get();
    } catch (InterruptedException | ExecutionException e) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_ID, "task not found");
    }
    File file = null;
    try {
      file = fileRepository.get(Id.create(params.fileId()).get()).get().get();
    } catch (InterruptedException | ExecutionException e) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_ID, "file not found");
    }
    var dateCreated = DateCreated.create();
    if (dateCreated.isLeft()) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_DATE_CREATED, "Date created");
    }
    //TODO проверять на null??
    return failure.getValues().size() == 0
            ? repository.add(task.getName(), task.getType(), dateCreated.get(), task.getAuthor(),
            file.getFormat(), file.getFileUrlPath(), file, task)
            : EitherHelper.badLeft(failure);
  }
}
