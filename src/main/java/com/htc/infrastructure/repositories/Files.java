package com.htc.infrastructure.repositories;

import com.htc.infrastructure.models.file.FileModel;
import java.util.concurrent.CompletableFuture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * JPA репозиторий файла.
 */
public interface Files extends JpaRepository<FileModel, Long> {
  @Async
  CompletableFuture<FileModel> findById(long id);
}
