package com.htc.domain.usecases.file;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.FilesRepository;
import com.htc.util.FileHelper;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

/**
 * Реализация сценария загрузки файла в базу данных.
 */
@Component
@AllArgsConstructor
public class UploadFile {

    /**
     * Поле для внедрения реализации из infrastructure layer.
     */
    FilesRepository filesRepository;

    /**
     * Метод сценария.
     *
     * @param fileName Имя файла.
     * @param composedUrl Составной URL.
     * @param dateCreated Дата создания файла.
     * @param taskId Идентификатор задачи.
     * @param file Файл.
     * @return void;
     */
    public CompletableFuture<Either<Failure, Void>> execute(String fileName, String composedUrl, LocalDate dateCreated, String taskId, File file) {
        var expectedFailure = ValuesValidator.validateStringId(taskId);
        if (expectedFailure != null) {
            return CompletableFuture.completedFuture(Either.left(expectedFailure));
        }
        var format = FileHelper.getFormat(file, fileName);
        return format.isLeft() ? CompletableFuture.completedFuture(Either.left(format.getLeft()))
                : filesRepository.uploadFile(fileName, dateCreated, format.get(), composedUrl, Integer.parseInt(taskId));
    }
}