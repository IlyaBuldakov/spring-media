package com.htc.application.services.impl;

import com.htc.application.dto.content.ContentResponse;
import com.htc.application.dto.errors.InternalServerErrorResponse;
import com.htc.application.services.ContentsService;
import com.htc.application.services.ExceptionDtoResolver;
import com.htc.domain.entities.failure.Failure;
import com.htc.domain.usecases.contents.CreateContent;
import com.htc.domain.usecases.contents.DeleteContentById;
import com.htc.domain.usecases.contents.GetAllContent;
import com.htc.domain.usecases.file.SaveFile;
import com.htc.util.FileHelper;
import io.vavr.control.Either;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Реализация {@link ContentsService}.
 */
@Service
@AllArgsConstructor
public class ContentsServiceImpl implements ContentsService {

  GetAllContent getAllContent;
  CreateContent createContent;
  DeleteContentById deleteContentById;

  SaveFile saveFile;

  /**
   * Уточняющий элемент для формирования пути к файлу.
   */
  private static final String LOCAL_DIRECTORY_QUALIFIER = "content/";

  /**
   * Уточняющий элемент для формирования
   * URL файла и последующего доступа к нему
   * как к статическому ресурсу на сервере.
   */
  private static final String URL_QUALIFIER = "uploads/" + LOCAL_DIRECTORY_QUALIFIER;

  /**
   * Получение списка всего контента.
   *
   * @return Список контента.
   */
  public CompletableFuture<List<ContentResponse>> getAll() {
    return getAllContent.execute()
            .thenApply(either -> either.map(
                    list -> list.parallelStream()
                            .map(ContentResponse::new)
                            .toList()
            ).getOrElseThrow(ExceptionDtoResolver::resolve));
  }

  /**
   * Создание контента.
   *
   * @param multipartFile Файл.
   * @param taskId        Идентификатор задачи.
   * @return void.
   */
  @Override
  public CompletableFuture<Void> uploadContent(int authorId, MultipartFile multipartFile,
                                               String taskId) {
    String fileName = multipartFile.getOriginalFilename();
    String composedUrl = FileHelper.composeUrl(fileName, taskId);
    try {
      File file = new File("src/main/resources/formatContent.tmp");
      try (OutputStream stream = new FileOutputStream(file)) {
        stream.write(multipartFile.getBytes());
      }
      return createContent.execute(authorId, fileName, file, URL_QUALIFIER + composedUrl, taskId)
              .thenApply(either -> {
                if (either.isLeft()) {
                  throw ExceptionDtoResolver.resolve(either.getLeft());
                }
                var saveFileResult = saveContent(multipartFile, composedUrl);
                if (saveFileResult.isLeft()) {
                  throw ExceptionDtoResolver.resolve(saveFileResult.getLeft());
                }
                return null;
              });
    } catch (IOException e) {
      throw new InternalServerErrorResponse();
    }
  }

  /**
   * Сохранение контента в файловой системе
   * (в директории статических ресурсов).
   *
   * @param file        Файл.
   * @param composedUrl Составной url из директории и последовательности
   *                    случайных символов для уникальности.
   */
  @Override
  public Either<Failure, Void> saveContent(MultipartFile file, String composedUrl) {
    try {
      return saveFile.execute(file.getBytes(), LOCAL_DIRECTORY_QUALIFIER, composedUrl);
    } catch (IOException e) {
      throw new InternalServerErrorResponse();
    }
  }

  /**
   * Удаление контента по идентификатору.
   *
   * @param id Идентификатор контента.
   * @return void.
   */
  public CompletableFuture<Void> delete(String id) {
    return deleteContentById.execute(id)
            .thenApply(either -> {
              if (either.isLeft()) {
                throw ExceptionDtoResolver.resolve(either.getLeft());
              }
              return null;
            });
  }
}
