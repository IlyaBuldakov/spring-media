package com.htc.infrastructure.repositories;

import com.htc.infrastructure.models.file.FileModel;
import java.util.concurrent.CompletableFuture;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA репозиторий файла.
 */
public interface Files extends JpaRepository<FileModel, Long> {
  CompletableFuture<FileModel> findById(long id);
}
