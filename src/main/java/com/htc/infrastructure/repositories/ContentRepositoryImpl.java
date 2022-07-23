package com.htc.infrastructure.repositories;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.ContentRepository;
import com.htc.infrastructure.models.ContentModel;
import com.htc.utility.Results;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория контента.
 */
@Repository
public class ContentRepositoryImpl implements ContentRepository {
  @Autowired
  Contents contents;

  @Override
  public CompletableFuture<Either<Failure, Content>> upload(
      Content.Type type,
      String name,
      LocalDateTime dateCreated,
      int authorId,
      Content.Format format,
      String urlContent,
      String urlPreview,
      int taskId) {
    var content = new ContentModel(
        type, name, dateCreated,
        authorId, format, urlContent,
        urlPreview, taskId);
    return Results.succeed(contents.save(content));
  }

  @Override
  public CompletableFuture<Either<Failure, Void>> delete(int id) {
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, Collection<Content>>> getContentFeed() {
    return null;
  }

  /**
   * ORM для доступа к данным контента в СУБД.
   */
  public interface Contents extends JpaRepository<ContentModel, Integer> {

  }
}