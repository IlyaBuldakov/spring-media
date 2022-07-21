package com.htc.infrastructure.repositories;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.ContentRepository;
import com.htc.infrastructure.models.ContentModel;
import io.vavr.control.Either;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория медиаконтента.
 */
@Repository
@AllArgsConstructor
public class ContentRepositoryImpl implements ContentRepository {

  @Autowired
  Contents contents;

  @Override
  public CompletableFuture<Either<Failure, Content>> create(Content content) {
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, Content>> update(Content content) {
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, Void>> delete(Id id) {
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, Content>> get(Id id) {
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, Collection<Content>>> getAll() {
    return null;
  }

  /**
   * ORM для доступа к данным медиаконтента в СУБД.
   */
  public interface Contents extends JpaRepository<ContentModel, Integer> {

  }
}
