package com.htc.domain.usecases.task;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValueParam;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.entities.utility.parameters.file.FileName;
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
                       String description, String descriptionKey,
                       Long authorId, String authorKey,
                       Long executorId, String executorKey,
                       String dateExpired, String dateExpiredKey) {}

  private final TaskRepository repository;
  private final UserRepository userRepository;

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
    User author = null;
    try {
      author = userRepository.get(Id.create(params.authorId()).get()).get().get();
    } catch (InterruptedException | ExecutionException e) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_ID, "user not found (executor)");
    }
    User executor = null;
    try {
      executor = userRepository.get(Id.create(params.authorId()).get()).get().get();
    } catch (InterruptedException | ExecutionException e) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_ID, "user not found (executor)");
    }
    var dateExpired = DateCreated.create(params.dateExpired());
    if (dateExpired.isLeft()) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_DATE_CREATED, "Date expired");
    }
    //TODO description - тип данных / проверка на null и empty
    return failure.getValues().size() == 0
            ? repository.update(id.get(), name.get(), params.description(),
              author, executor, dateExpired.get())
            : EitherHelper.badLeft(failure);
  }
}
