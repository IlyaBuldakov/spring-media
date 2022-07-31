package com.htc.domain.service;

import com.htc.domain.entities.File;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.usecases.FileUseCase;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
public class FileService {
  private FileUseCase.Create create;
  private FileUseCase.DeleteById deleteById;
  private FileUseCase.GetAllByTask getAllByTask;

  /**
   * Создаёт пользователя.
   *
   * @param subjectId Идентификатор пользователя, выполняющего данную операцию.
   * @param file Файл.
   * @param taskId Идентификатор родительской задачи.
   * @return Ошибка или пользователь.
   */
  public Either<Failure, File> create(
      Id subjectId,
      MultipartFile file,
      Id taskId
  ) {
    return this.create.execute(
            subjectId,
            new FileUseCase.Create.Params(file, taskId)
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
