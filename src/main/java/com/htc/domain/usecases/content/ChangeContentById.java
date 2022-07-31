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

/**
 * Сценарий изменения контента.
 */
@AllArgsConstructor
public final class ChangeContentById implements UseCase<ChangeContentById.Params, Content> {
  /**
   * Параметры сценария изменения контента.
   *
   * @param id идентификатор контента
   * @param taskId идентификатор задачи, относящийся к контенту
   * @param fileId идентификатор файла, относящийся к контенту
   */
  public record Params(Id id, Id taskId, Id fileId) {}

  private final ContentRepository repository;
  private final FileRepository fileRepository;
  private final TaskRepository taskRepository;

  @Override
  public CompletableFuture<Either<Failure, Content>> execute(Params params) {
    var failure = new InvalidValues();
    Task task = null;
    try {
      task = taskRepository.get(params.taskId()).get().get();
    } catch (InterruptedException | ExecutionException e) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_ID, "task not found");
    }
    File file = null;
    try {
      file = fileRepository.get(params.fileId()).get().get();
    } catch (InterruptedException | ExecutionException e) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_ID, "file not found");
    }
    //TODO нужно ли переписывать дату?? в задачах не переписывает
    var dateCreated = DateCreated.create();
    if (dateCreated.isLeft()) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_DATE_CREATED, "Date created");
    }
    //TODO реализовать проверку на null в getName и getFormat
    return failure.getValues().size() == 0
            ? repository.change(params.id(), task.getName(), task.getType(), dateCreated.get(),
            task.getAuthor(), file.getFormat(), file.getFileUrlPath(), file, task)
            : EitherHelper.badLeft(failure);
  }
}
