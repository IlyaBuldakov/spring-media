package com.htc.domain.usecases.file;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.FilesRepository;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.concurrent.CompletableFuture;

@Component
@AllArgsConstructor
public class DeleteFile {

    FilesRepository filesRepository;

    public CompletableFuture<Either<Failure, Void>> execute(String directoryQualifier, String fileId) {
        var expectedFailure = ValuesValidator.validateStringId(fileId);
        if (expectedFailure != null) {
            return CompletableFuture.completedFuture(Either.left(expectedFailure));
        }
        var file = filesRepository.findFileUrlById(Integer.parseInt(fileId));
        if (file.isLeft()) {
            return CompletableFuture.completedFuture(Either.left(file.getLeft()));
        }
        new File(directoryQualifier + file.get()).delete();
        return filesRepository.deleteFile(Integer.parseInt(fileId));
    }
}
