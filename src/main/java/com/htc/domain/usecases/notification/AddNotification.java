package com.htc.domain.usecases.notification;

import com.htc.domain.entities.content.Type;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValueParam;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.notification.Notification;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.repositories.NotificationRepository;
import com.htc.domain.repositories.TaskRepository;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utility.EitherHelper;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;

/**
 * Сценарий добавления уведомления.
 */
@AllArgsConstructor
public final class AddNotification implements UseCase<AddNotification.Params, Notification> {
  /**
   * Параметры сценария добавления уведомления.
   *
   * @param type тип уведомления
   * @param typeKey ключ типа уведомления
   * @param dateNotification дата уведомления
   * @param dateNotificationKey ключ даты уведомления
   * @param message сообщение уведомления
   * @param messageKey ключ сообщения уведомления
   * @param userId идентификатор пользователя
   * @param userIdKey ключ идентификатора пользователя
   * @param taskId идентификатор задачи
   * @param taskIdKey ключ идентификатора задачи
   */
  public record Params(Type type, String typeKey,
                       String dateNotification, String dateNotificationKey,
                       String message, String messageKey,
                       Long userId, String userIdKey,
                       Long taskId, String taskIdKey) {}

  private final NotificationRepository repository;

  private final UserRepository userRepository;
  private final TaskRepository taskRepository;

  @Override
  public CompletableFuture<Either<Failure, Notification>> execute(Params params) {
    var failure = new InvalidValues();
    var dateNotification = DateCreated.create(params.dateNotification());
    if (dateNotification.isLeft()) {
      failure.getValues().put(
              InvalidValueParam.INVALID_ENTITY_DATE_CREATED, params.dateNotificationKey);
    }
    User user = null;
    try {
      user = userRepository.get(Id.create(params.userId()).get()).get().get();
    } catch (InterruptedException | ExecutionException e) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_ID, params.userIdKey);
    }
    Task task = null;
    try {
      task = taskRepository.get(Id.create(params.taskId()).get()).get().get();
    } catch (InterruptedException | ExecutionException e) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_ID, params.taskIdKey);
    }
    return failure.getValues().size() == 0
            ? repository.add(params.type(), dateNotification.get(), params.message(), user, task)
            : EitherHelper.badLeft(failure);
  }
}
