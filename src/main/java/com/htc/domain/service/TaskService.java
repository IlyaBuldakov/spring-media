package com.htc.domain.service;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.Task;
import com.htc.domain.entities.User;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.usecases.TaskUseCase;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.Collection;
import lombok.AllArgsConstructor;

/**
 * Фасад для сценариев работы с задачами.
 */
@AllArgsConstructor
public class TaskService {
  private TaskUseCase.Create create;
  private TaskUseCase.DeleteById deleteById;
  private TaskUseCase.GetAll getAll;
  private TaskUseCase.GetById getById;
  private TaskUseCase.Update update;

  /**
   * Создаёт задачу.
   *
   * @param subjectId Идентификатор пользователя, выполняющего данную операцию.
   * @param name Название задачи.
   * @param contentType Тип медиаконтента.
   * @param description Описание задачи.
   * @param author Автор задачи.
   * @param executor Исполнитель задачи.
   * @param dateExpired Дата окончания срока выполнения задачи.
   * @return Ошибка или задача.
   */
  public Either<Failure, Task> create(
          Id subjectId,
          Task.Name name,
          Content.Type contentType,
          Task.Description description,
          User author,
          User executor,
          LocalDateTime dateExpired
  ) {
    return this.create.execute(
            subjectId,
            new TaskUseCase.Create.Params(name, contentType, description, author, executor, dateExpired)
    );
  }

  /**
   * Удаляет задачу по его идентификатору.
   *
   * @param subjectId Идентификатор пользователя, выполняющего данную операцию.
   * @param targetId Идентификатор задачи.
   * @return Ошибка или ничего.
   */
  public Either<Failure, Void> delete(Id subjectId, Id targetId) {
    return this.deleteById.execute(subjectId, targetId);
  }

  /**
   * Получает задачу по ее идентификатору.
   *
   * @param subjectId Идентификатор пользователя, выполняющего данную операцию.
   * @param targetId Идентификатор задачи.
   * @return Ошибка или пользователь.
   */
  public Either<Failure, Task> get(Id subjectId, Id targetId) {
    return this.getById.execute(subjectId, targetId);
  }

  /**
   * Получает список всех задач.
   *
   * @param subjectId Идентификатор пользователя, выполняющего данную операцию.
   * @return Ошибка или список задач.
   */
  public Either<Failure, Collection<Task>> getAll(Id subjectId) {
    return this.getAll.execute(
            subjectId,
            new UseCase.NoParams());
  }


  /**
   * Обновляет данные задачи.
   *
   * @param subjectId Идентификатор пользователя, выполняющего данную операцию.
   * @param id Идентификатор задачи.
   * @param name Название задачи.
   * @param contentType Тип медиаконтента.
   * @param description Описание задачи.
   * @param author Автор задачи.
   * @param executor Исполнитель задачи.
   * @param dateExpired Срок задачи.
   * @return Ошибка или задача.
   */
  public Either<Failure, Task> update(
          Id subjectId,
          Id id,
          Task.Name name,
          Content.Type contentType,
          Task.Description description,
          User author,
          User executor,
          LocalDateTime dateExpired
  ) {
    return this.update.execute(
            subjectId,
            new TaskUseCase.Update.Params(id, name, contentType, description, author, executor, dateExpired)
    );
  }
}
