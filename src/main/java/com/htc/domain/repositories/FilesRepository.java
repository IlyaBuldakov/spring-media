package com.htc.domain.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.file.File.Format;
import io.vavr.control.Either;

import java.time.LocalDate;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface FilesRepository {

    void uploadFile(String name, LocalDate dateCreated, Format format, String url, int taskId);

    CompletableFuture<Either<Failure, Void>> deleteFile(int fileId);

    Either<Failure, String> findFileUrlById(int fileId);

    Set<String> findRelevantToTaskFiles(int taskId);
}
