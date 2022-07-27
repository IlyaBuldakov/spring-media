package com.htc.infrastructure.repositories;

import com.htc.infrastructure.models.task.TaskModel;
import java.util.concurrent.CompletableFuture;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA репозиторий задачи.
 */
public interface Tasks extends JpaRepository<TaskModel, Long> {
  CompletableFuture<TaskModel> findById(long id);
}
