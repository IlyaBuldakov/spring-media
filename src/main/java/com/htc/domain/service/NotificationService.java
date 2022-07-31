package com.htc.domain.service;

import com.htc.domain.entities.Notification;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.usecases.NotificationUseCase;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.Collection;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotificationService {

  private NotificationUseCase.GetAll getAll;

  /**
   * Получает список всех уведомлений.
   *
   * @param subjectId Идентификатор пользователя, выполняющего данную операцию.
   * @return Ошибка или список уведомлений.
   */
  public Either<Failure, Collection<Notification>> getAll(Id subjectId) {
    return this.getAll.execute(
            subjectId,
            new UseCase.NoParams());
  }
}
