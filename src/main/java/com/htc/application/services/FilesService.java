package com.htc.application.services;

import com.htc.domain.entities.failure.Failure;
import io.vavr.control.Either;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.multipart.MultipartFile;

/**
 * Интерфейс, описывающий базовые операции для взаимодействия с файлами.
 * Слой преобразования DTO <---> Domain entity.
 */
public interface FilesService {

  /**
   * Загрузка файла в базу данных.
   *
   * @param file   Файл.
   * @param taskId Идентификатор задачи, к которой загружается файл.
   */
  CompletableFuture<Void> uploadFile(Collection<? extends GrantedAuthority> authorities,
                                     MultipartFile file, String taskId);

  /**
   * Удаление файла по идентификатору.
   *
   * @param fileId Идентификатор файла.
   */
  CompletableFuture<Void> deleteFile(Collection<? extends GrantedAuthority> authorities,
                                     String fileId);

  /**
   * Сохранение файла в файловой системе.
   *
   * @param file        Файл.
   * @param composedUrl Составной url из директории и последовательности
   *                    случайных символов для уникальности.
   */
  Either<Failure, Void> saveFile(MultipartFile file, String composedUrl);
}
