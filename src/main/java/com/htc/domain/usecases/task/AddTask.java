package com.htc.domain.usecases.task;

import com.htc.domain.entities.content.Type;
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
import org.springframework.stereotype.Component;

/**
 * Сценарий добавления новой задачи.
 */
//@Component
@AllArgsConstructor
public final class AddTask implements UseCase<AddTask.Params, Task> {
  /**
   * Параметры сценария добавления задачи.
   */
  public record Params(String name, String nameKey,
                       Type type, String typeKey,
                       String description, String descriptionKey,
                       Long authorId, String authorKey,
                       Long executorId, String executorKey,
                       String dateExpired, String dateExpiredKey) {}

  private final TaskRepository repository;
  private final UserRepository userRepository;

  @Override
  public CompletableFuture<Either<Failure, Task>> execute(Params params) {
    var failure = new InvalidValues();
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
    var dateCreated = DateCreated.create();
    if (dateCreated.isLeft()) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_DATE_CREATED, "Date created");
    }
    var dateExpired = DateCreated.create(params.dateExpired());
    if (dateExpired.isLeft()) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_DATE_CREATED, "Date expired");
    }
    //TODO description - тип данных / проверка на null и empty
    return failure.getValues().size() == 0
            ? repository.add(name.get(), params.type(), params.description(), author, executor,
                          dateCreated.get(), dateExpired.get())
            : EitherHelper.badLeft(failure);
  }
}
