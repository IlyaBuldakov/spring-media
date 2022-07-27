package com.htc.application.services.impl;

import com.htc.application.dto.errors.InternalServerErrorResponse;
import com.htc.application.services.ExceptionDtoResolver;
import com.htc.application.services.FilesService;
import com.htc.domain.usecases.file.DeleteFile;
import com.htc.domain.usecases.file.SaveFile;
import com.htc.domain.usecases.file.UploadFile;
import com.htc.util.FileHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

/**
 * Реализация {@link FilesService}.
 */
@Service
@AllArgsConstructor
public class FilesServiceImpl implements FilesService {

    UploadFile uploadFile;
    SaveFile saveFile;
    DeleteFile deleteFile;

    /**
     * Уточняющий элемент для формирования
     * URL файла и последующего доступа к нему
     * как к статическому ресурсу на сервере.
     */
    private final String URL_QUALIFIER = "uploads/files/";

    /**
     * Уточняющий элемент для формирования пути
     * к файлу в файловой системе (в директории статических ресурсов).
     */
    private final String DIRECTORY_MAIN_QUALIFIER = "src/main/webapp/";

    /**
     * Загрузка файла в базу данных.
     *
     * @param multipartFile Файл.
     * @param taskId Идентификатор задачи, к которой загружается файл.
     * @return void.
     */
    @Override
    public CompletableFuture<Void> uploadFile(MultipartFile multipartFile, String taskId) {
        String fileName = multipartFile.getOriginalFilename();
        String composedUrl = composeUrl(fileName, taskId);
        try {
            File file = new File("src/main/resources/tmp/formatFile.tmp");
            try (OutputStream stream = new FileOutputStream(file)) {
                stream.write(multipartFile.getBytes());
            }
            return uploadFile.execute(fileName, URL_QUALIFIER + composedUrl, LocalDate.now(), taskId, file)
                    .thenApply(either -> {
                        if (either.isLeft()) {
                            throw ExceptionDtoResolver.resolve(either.getLeft());
                        }
                        saveFile(multipartFile, composedUrl);
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
     * @param file Файл.
     * @param composedUrl Составной url из директории и последовательности
     *                    случайных символов для уникальности.
     */
    @Override
    public void saveFile(MultipartFile file, String composedUrl) {
        if (!file.isEmpty()) {
            try {
                String directorySaveQualifier = DIRECTORY_MAIN_QUALIFIER + URL_QUALIFIER;
                saveFile.execute(file.getBytes(), directorySaveQualifier + composedUrl);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
        return deleteFile.execute(DIRECTORY_MAIN_QUALIFIER, fileId)
                .thenApply(either -> {
                    if (either.isLeft()) {
                        throw ExceptionDtoResolver.resolve(either.getLeft());
                    }
                    return null;
                });
    }

    /**
     * Генерация составного URL, состоящего из
     * случайной строки (для уникальности),
     * имени файла и идентификатора задачи.
     *
     * @param fileName Имя файла.
     * @param taskId Идентификатор задачи.
     * @return Составной URL.
     */
    private String composeUrl(String fileName, String taskId) {
        return FileHelper.getRandomString() +
                "-uploaded_task-" +
                taskId +
                "_" +
                fileName;
    }
}
