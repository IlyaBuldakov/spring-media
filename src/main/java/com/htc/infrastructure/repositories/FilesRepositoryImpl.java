package com.htc.infrastructure.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.entities.file.File;
import com.htc.domain.repositories.FilesRepository;
import com.htc.infrastructure.mappers.FileMapper;
import com.htc.infrastructure.jpa.FilesJpaRepository;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class FilesRepositoryImpl implements FilesRepository {

    FilesJpaRepository filesJpaRepository;

    @Override
    public void uploadFile(String name, LocalDate dateCreated, File.Format format, String url, int taskId) {
        filesJpaRepository.save(new FileMapper(name, dateCreated, format, url, taskId));
    }

    @Override
    public CompletableFuture<Either<Failure, Void>> deleteFile(int fileId) {
        try {
            filesJpaRepository.deleteById(fileId);
        } catch (EmptyResultDataAccessException exception) {
            return CompletableFuture.completedFuture(Either.left(NotFound.FILE));
        }
        return CompletableFuture.completedFuture(Either.right(null));
    }

    @Override
    public Either<Failure, String> findFileUrlById(int fileId) {
        var file = filesJpaRepository.findById(fileId);
        if (file.isPresent()) {
            return Either.right(file.get().getUrl());
        }
        return Either.left(NotFound.FILE);
    }

    @Override
    public Set<String> findRelevantToTaskFiles(int taskId) {
        return filesJpaRepository.findFileMappersByTaskId(taskId)
                .parallelStream().map(FileMapper::getUrl).collect(Collectors.toSet());
    }
}
