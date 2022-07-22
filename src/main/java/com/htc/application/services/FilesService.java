package com.htc.application.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

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
    void saveFile(MultipartFile file, String composedUrl);
}
