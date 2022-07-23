package com.htc.domain.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.file.File.Format;
import io.vavr.control.Either;

import java.time.LocalDate;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий файлов.
 */
public interface FilesRepository {

    /**
     * Загрузка файла в базу данных.
     *
     * @param name Имя файла.
     * @param dateCreated Дата создания файла.
     * @param format Формат файла.
     * @param url URL файла.
     * @param taskId Идентификатор задачи.
     */
    CompletableFuture<Either<Failure, Void>> uploadFile(String name, LocalDate dateCreated, Format format, String url, int taskId);

    /**
     * Удаление файла из базы данных и файловой системы
     * (директории статических ресурсов).
     *
     * @param fileId Идентификатор файла.
     * @return void.
     */
    CompletableFuture<Either<Failure, Void>> deleteFile(int fileId);

    /**
     * Получение URL файла по его идентификатору.
     *
     * @param fileId Идентификатор файла.
     * @return URL файла.
     */
    Either<Failure, String> findFileUrlById(int fileId);

    /**
     * Получение множества URL файлов по идентификатору
     * задачи, которые относятся к этой задаче
     *
     * @param taskId Идентификатор задачи.
     * @return Множество URL файлов.
     */
    Set<String> findRelevantToTaskFilesUrl(int taskId);
}
