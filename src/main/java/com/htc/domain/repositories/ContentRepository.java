package com.htc.domain.repositories;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.Type;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.file.File;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.entities.utility.parameters.file.FileName;
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
   * @param author автор контента
   * @param previewPath превью контента
   * @param file файл, относящийся к контенту
   *
   * @return content новый контент, подробнее {@link Content}
   */
  CompletableFuture<Either<Failure, Content>> add(FileName name, Type type, User author,
                                                  FileUrlPath previewPath, File file);

  // +addFile отдельно

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
   * @param author автор контента
   * @param previewPath превью контента
   * @param file файл, относящийся к контенту
   *
   * @return content новый контент, подробнее {@link Content}
   */
  CompletableFuture<Either<Failure, Content>> change(Id id, FileName name, Type type, User author,
                                                     FileUrlPath previewPath, File file);

  /**
   * Удаление контента по идентификатору.
   *
   * @param id идентификатор контента
   */
  CompletableFuture<Either<Failure, Void>> delete(Id id);
}
