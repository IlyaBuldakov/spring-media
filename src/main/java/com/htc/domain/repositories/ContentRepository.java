package com.htc.domain.repositories;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.Type;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.file.File;
import com.htc.domain.entities.file.Format;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.EntityName;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.entities.utility.parameters.file.FileUrlPath;
import io.vavr.control.Either;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий для контента.
 */
public interface ContentRepository {
  /**
   * Добавление нового контента.
   *
   * @param name наименование контента
   * @param type тип контента
   * @param dateCreated дата создания
   * @param author автор контента
   * @param previewPath превью контента
   * @param file файл, относящийся к контенту
   * @param task задача, относящаяся к контенту
   * @return content новый контент, подробнее {@link Content}
   */
  CompletableFuture<Either<Failure, Content>> add(EntityName name, Type type,
                                                  DateCreated dateCreated, User author,
                                                  Format format, FileUrlPath previewPath,
                                                  File file, Task task);

  /**
   * Получение контента по идентификатору.
   *
   * @param id идентификатор контента
   * @return content контент, подробнее {@link Content}
   */
  CompletableFuture<Either<Failure, Content>> get(Id id);

  /**
   * Получение ленты контента.
   *
   * @return list лента контента, подробнее {@link Content}
   */
  CompletableFuture<Either<Failure, List<Content>>> getAll();

  /**
   * Изменение контента.
   *
   * @param id идентификатор контента
   * @param name наименование контента
   * @param type тип контента
   * @param dateCreated дата создания
   * @param author автор контента
   * @param previewPath превью контента
   * @param file файл, относящийся к контенту
   * @param task задача, относящаяся к контенту
   * @return content измененный контент, подробнее {@link Content}
   */
  CompletableFuture<Either<Failure, Content>> change(Id id, EntityName name, Type type,
                                                     DateCreated dateCreated, User author,
                                                     Format format, FileUrlPath previewPath,
                                                     File file, Task task);

  /**
   * Удаление контента по идентификатору.
   *
   * @param id идентификатор контента
   */
  CompletableFuture<Either<Failure, Void>> delete(Id id);
}
