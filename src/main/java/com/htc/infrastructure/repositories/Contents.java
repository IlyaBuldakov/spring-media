package com.htc.infrastructure.repositories;

import com.htc.infrastructure.models.content.ContentModel;
import java.util.concurrent.CompletableFuture;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA репозиторий контента.
 */
public interface Contents extends JpaRepository<ContentModel, Long> {
  CompletableFuture<ContentModel> findById(long id);
}
