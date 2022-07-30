package com.htc.domain.usecases.file;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValue;
import io.vavr.control.Either;
import org.springframework.stereotype.Component;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

/**
 * Реализация сценария сохранения файла
 * в файловой системе (директории статических ресурсов)
 */
@Component
public class SaveFile {

    private final String directoryQualifier = "src/main/webapp/uploads/";

    /**
     * Метод сценария.
     *
     * @param fileBinary Файл.
     * @param composedUrl Составной url.
     */
    public Either<Failure, Void> execute(byte[] fileBinary, String localDirectoryQualifier, String composedUrl) {
        if (fileBinary.length == 0) {
            return Either.left(InvalidValue.EMPTY_FILE);
        }
        try {
            String fullPath = directoryQualifier + localDirectoryQualifier + composedUrl;
            Files.createDirectories(Paths.get(directoryQualifier + localDirectoryQualifier));
            new File(fullPath);
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(fullPath));
            stream.write(fileBinary);
            stream.close();
            return Either.right(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
