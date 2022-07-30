package com.htc.application.services;

import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

/**
 * Интерфейс, описывающий базовые операции для взаимодействия с файлами.
 * Слой преобразования DTO <---> Domain entity.
 */
public interface FilesService {

    /**
     * Загрузка файла в базу данных.
     *
     * @param file Файл.
     * @param taskId Идентификатор задачи, к которой загружается файл.
     */
    CompletableFuture<Void> uploadFile(MultipartFile file, String taskId);

    /**
     * Удаление файла по идентификатору.
     *
     * @param fileId Идентификатор файла.
     */
    CompletableFuture<Void> deleteFile(String fileId);

    /**
     * Сохранение файла в файловой системе.
     *
     * @param file Файл.
     * @param composedUrl Составной url из директории и последовательности
     *                    случайных символов для уникальности.
     */
    Either<Failure, Void> saveFile(MultipartFile file, String composedUrl);
}
