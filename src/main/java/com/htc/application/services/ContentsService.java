package com.htc.application.services;

import com.htc.application.dto.content.ContentResponse;
import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.web.multipart.MultipartFile;

/**
 * Интерфейс, описывающий базовые операции для взаимодействия с контентом.
 * Слой преобразования DTO <---> Domain entity.
 */
public interface ContentsService {

  /**
   * Получение списка контента.
   *
   * @return Список контента.
   */
  CompletableFuture<List<ContentResponse>> getAll();

  /**
   * Создание контента.
   *
   * @param file   Файл контента.
   * @param taskId Идентификатор задачи.
   * @return void.
   */
  CompletableFuture<Void> uploadContent(int authorId, MultipartFile file, String taskId);

  /**
   * Сохранение контента в файловой системе.
   *
   * @param file        Файл.
   * @param composedUrl Составной url из директории и последовательности
   *                    случайных символов для уникальности.
   */
  Either<Failure, Void> saveContent(MultipartFile file, String composedUrl);

  /**
   * Удаление контента по идентификатору.
   *
   * @param id Идентификатор контента.
   * @return void.
   */
  CompletableFuture<Void> delete(String id);
}
