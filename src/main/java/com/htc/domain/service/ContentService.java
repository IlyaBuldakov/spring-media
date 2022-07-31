package com.htc.domain.service;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.User;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.usecases.ContentUseCase;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import org.springframework.web.multipart.MultipartFile;

public class ContentService {
  private ContentUseCase.Create create;
  private ContentUseCase.DeleteById deleteById;
  private ContentUseCase.GetFeed getFeed;

  /**
   * Создаёт медиаконтент.
   *
   * @param subjectId Идентификатор пользователя, выполняющего данную операцию.
   * @param file Файл.
   * @param taskId Индентификатор задачи.
   * @return Ошибка или контент.
   */
  public Either<Failure, Content> create(
          Id subjectId,
          MultipartFile file,
          Id taskId
  ) {
    return this.create.execute(
            subjectId,
            new ContentUseCase.Create.Params(file, taskId, subjectId));
  }

  /**
   * Удаляет медиаконтент по его идентификатору.
   *
   * @param subjectId Идентификатор пользователя, выполняющего данную операцию.
   * @param targetId Идентификатор медиаконтента.
   * @return Ошибка или ничего.
   */
  public Either<Failure, Void> delete(Id subjectId, Id targetId) {
    return this.deleteById.execute(subjectId, targetId);
  }

  /**
   * Получает ленту контента.
   *
   * @param subjectId Идентификатор пользователя, выполняющего данную операцию.
   * @return Ошибка или список пользователей.
   */
  public Either<Failure, Collection<Content>> getFeed(
          Id subjectId,
          Integer page,
          Integer count,
          String author,
          String dateCreated,
          Integer typeId) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate date = LocalDate.parse(dateCreated, formatter);
    return this.getFeed.execute(
            subjectId,
            new ContentUseCase.GetFeed.Params(page, count, author, date, typeId));
  }

}
