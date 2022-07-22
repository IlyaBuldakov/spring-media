package com.htc.application.services.impl;

import com.htc.application.services.ExceptionDtoResolver;
import com.htc.application.services.FilesService;
import com.htc.domain.usecases.file.DeleteFile;
import com.htc.domain.usecases.file.SaveFile;
import com.htc.domain.usecases.file.UploadFile;
import com.htc.util.FileHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class FilesServiceImpl implements FilesService {

    UploadFile uploadFile;
    SaveFile saveFile;
    DeleteFile deleteFile;

    private final String URL_QUALIFIER = "uploads/files/";
    private final String DIRECTORY_MAIN_QUALIFIER = "src/main/webapp/";
    private final String DIRECTORY_SAVE_QUALIFIER = DIRECTORY_MAIN_QUALIFIER + URL_QUALIFIER;

    @Override
    public CompletableFuture<Void> uploadFile(MultipartFile file, String taskId) {
        String fileName = file.getOriginalFilename();
        String composedUrl = composeUrl(fileName, taskId);
        try {
            return uploadFile.execute(fileName, URL_QUALIFIER + composedUrl, LocalDate.now(), taskId, file.getBytes())
                    .thenApply(either -> {
                        if (either.isLeft()) {
                            throw ExceptionDtoResolver.resolve(either.getLeft());
                        }
                        saveFile(file, composedUrl);
                        return null;
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveFile(MultipartFile file, String composedUrl) {
        if (!file.isEmpty()) {
            try {
                saveFile.execute(file.getBytes(), DIRECTORY_SAVE_QUALIFIER + composedUrl);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

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

    private String composeUrl(String fileName, String taskId) {
        return FileHelper.getRandomString() +
                "-uploaded_task-" +
                taskId +
                "_" +
                fileName;
    }
}
