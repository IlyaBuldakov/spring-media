package com.htc.domain.service;

import com.htc.domain.entities.File;
import com.htc.domain.entities.Task;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.usecases.FileUseCase;
import io.vavr.control.Either;
import java.time.LocalDateTime;

public class FileService {
  private FileUseCase.Create create;
  private FileUseCase.DeleteById deleteById;

  /**
   * Создаёт пользователя.
   *
   * @param subjectId Идентификатор пользователя, выполняющего данную операцию.
   * @param name Имя файла.
   * @param dateCreated Дата создания файла.
   * @param format Формат файла.
   * @param url Адрес файла.
   * @param task Индентификатор родительской задачи.
   * @return Ошибка или пользователь.
   */
  public Either<Failure, File> create(
          Id subjectId,
          File.Name name,
          LocalDateTime dateCreated,
          File.Format format,
          File.Url url,
          Task task
  ) {
    return this.create.execute(
            subjectId,
            new FileUseCase.Create.Params(name, dateCreated, format, url, task)
    );
  }

  /**
   * Удаляет файл по его идентификатору.
   *
   * @param subjectId Идентификатор пользователя, выполняющего данную операцию.
   * @param targetId Идентификатор файла.
   * @return Ошибка или ничего.
   */
  public Either<Failure, Void> delete(Id subjectId, Id targetId) {
    return this.deleteById.execute(subjectId, targetId);
  }

}
