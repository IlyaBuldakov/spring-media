package com.htc.domain.usecases.file;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValue;
import com.htc.domain.entities.file.File.Format;
import com.htc.domain.repositories.FilesRepository;
import com.htc.util.FileHelper;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

@Component
@AllArgsConstructor
public class UploadFile {

    FilesRepository filesRepository;

    public CompletableFuture<Either<Failure, Void>> execute(String fileName, String composedUrl, LocalDate dateCreated, String taskId, byte[] fileBinary) {
        var expectedFailure = ValuesValidator.validateStringId(taskId);
        if (expectedFailure != null) {
            return CompletableFuture.completedFuture(Either.left(expectedFailure));
        }
        var format = FileHelper.getFormat(fileBinary, fileName);
        if (format.isLeft()) {
            return CompletableFuture.completedFuture(Either.left(format.getLeft()));
        }
        filesRepository.uploadFile(fileName, dateCreated, format.get(), composedUrl, Integer.parseInt(taskId));
        return CompletableFuture.completedFuture(Either.right(null));
    }
}
