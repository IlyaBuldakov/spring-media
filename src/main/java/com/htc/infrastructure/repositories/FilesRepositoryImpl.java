package com.htc.infrastructure.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.entities.file.File;
import com.htc.domain.repositories.FilesRepository;
import com.htc.infrastructure.jpa.FilesJpaRepository;
import com.htc.infrastructure.jpa.TasksJpaRepository;
import com.htc.infrastructure.mappers.FileMapper;
import io.vavr.control.Either;
import java.time.LocalDate;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

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

  TasksJpaRepository tasksJpaRepository;

  /**
   * Загрузка файла в базу данных.
   *
   * @param name        Имя файла.
   * @param dateCreated Дата создания файла.
   * @param fileFormat  Формат файла.
   * @param url         URL файла.
   * @param taskId      Идентификатор задачи.
   */
  @Override

  public CompletableFuture<Either<Failure, Void>> uploadFile(String name, LocalDate dateCreated,
                                                             File.FileFormat fileFormat,
                                                             String url, int taskId) {
    if (tasksJpaRepository.existsById(taskId)) {
      filesJpaRepository.save(new FileMapper(name, dateCreated, fileFormat, url, taskId));
      return CompletableFuture.completedFuture(Either.right(null));
    }
    return CompletableFuture.completedFuture(Either.left(NotFound.TASK));
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
