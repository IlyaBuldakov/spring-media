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

/**
 * Реализация репозитория для работы с файлами.
 */
@Component
@AllArgsConstructor
public class FilesRepositoryImpl implements FilesRepository {

    /**
     * JPA-репозиторий.
     */
    FilesJpaRepository filesJpaRepository;

    /**
     * Загрузка файла в базу данных.
     *
     * @param name Имя файла.
     * @param dateCreated Дата создания файла.
     * @param format Формат файла.
     * @param url URL файла.
     * @param taskId Идентификатор задачи.
     */
    @Override
    public void uploadFile(String name, LocalDate dateCreated, File.Format format, String url, int taskId) {
        filesJpaRepository.save(new FileMapper(name, dateCreated, format, url, taskId));
    }

    /**
     * Удаление файла из базы данных.
     *
     * @param fileId Идентификатор файла.
     * @return void.
     */
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

    /**
     * Поиск URL файлов, относящихся к задаче.
     *
     * @param taskId Идентификатор задачи.
     * @return Множество URL.
     */
    @Override
    public Set<String> findRelevantToTaskFilesUrl(int taskId) {
        return filesJpaRepository.findFileMappersByTaskId(taskId)
                .parallelStream().map(FileMapper::getUrl).collect(Collectors.toSet());
    }
}
