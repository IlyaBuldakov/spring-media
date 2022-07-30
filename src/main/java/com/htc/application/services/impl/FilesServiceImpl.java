package com.htc.application.services.impl;

import com.htc.application.dto.errors.InternalServerErrorResponse;
import com.htc.application.services.ExceptionDtoResolver;
import com.htc.application.services.FilesService;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.usecases.file.DeleteFile;
import com.htc.domain.usecases.file.SaveFile;
import com.htc.domain.usecases.file.UploadFile;
import com.htc.util.FileHelper;
import io.vavr.control.Either;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Реализация {@link FilesService}.
 */
@Service
@AllArgsConstructor
public class FilesServiceImpl implements FilesService {

  UploadFile uploadFile;
  SaveFile saveFile;
  DeleteFile deleteFile;

  private static final String LOCAL_DIRECTORY_QUALIFIER = "files/";

  /**
   * Уточняющий элемент для формирования
   * URL файла и последующего доступа к нему
   * как к статическому ресурсу на сервере.
   */
  private static final String URL_QUALIFIER = "uploads/" + LOCAL_DIRECTORY_QUALIFIER;

  /**
   * Загрузка файла в базу данных.
   *
   * @param multipartFile Файл.
   * @param taskId        Идентификатор задачи, к которой загружается файл.
   * @return void.
   */
  @Override
  public CompletableFuture<Void> uploadFile(MultipartFile multipartFile, String taskId) {
    String fileName = multipartFile.getOriginalFilename();
    String composedUrl = FileHelper.composeUrl(fileName, taskId);
    try {
      File file = new File("src/main/resources/formatFile.tmp");
      try (OutputStream stream = new FileOutputStream(file)) {
        stream.write(multipartFile.getBytes());
      }
      return uploadFile.execute(fileName, URL_QUALIFIER + composedUrl, LocalDate.now(), taskId, file)
              .thenApply(either -> {
                if (either.isLeft()) {
                  throw ExceptionDtoResolver.resolve(either.getLeft());
                }
                var saveFileResult = saveFile(multipartFile, composedUrl);
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
   * Сохранение файла в файловой системе
   * (в директории статических ресурсов).
   *
   * @param file        Файл.
   * @param composedUrl Составной url из директории и последовательности
   *                    случайных символов для уникальности.
   */
  @Override
  public Either<Failure, Void> saveFile(MultipartFile file, String composedUrl) {
    try {
      return saveFile.execute(file.getBytes(), LOCAL_DIRECTORY_QUALIFIER, composedUrl);
    } catch (IOException e) {
      throw new InternalServerErrorResponse();
    }
  }

  /**
   * Удаление файла из файловой системы
   * (из директории статических ресурсов).
   *
   * @param fileId Идентификатор файла.
   * @return void.
   */
  @Override
  public CompletableFuture<Void> deleteFile(String fileId) {
    return deleteFile.execute(fileId)
            .thenApply(either -> {
              if (either.isLeft()) {
                throw ExceptionDtoResolver.resolve(either.getLeft());
              }
              return null;
            });
  }
}