package com.htc.infrastructure.repositories;

import com.htc.infrastructure.models.task.TaskModel;
import java.util.concurrent.CompletableFuture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * JPA репозиторий задачи.
 */
public interface Tasks extends JpaRepository<TaskModel, Long> {
  @Async
  CompletableFuture<TaskModel> findById(long id);
}
