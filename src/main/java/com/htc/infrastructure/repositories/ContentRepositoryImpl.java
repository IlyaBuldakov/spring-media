package com.htc.infrastructure.repositories;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.ContentRepository;
import com.htc.infrastructure.models.ContentModel;
import com.htc.utility.Results;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
      int taskId,
      boolean approve) {
    var content = new ContentModel(
        type, name, dateCreated,
        authorId, format, urlContent,
        urlPreview, taskId, approve);
    return Results.succeed(contents.save(content));
  }

  @Override
  public CompletableFuture<Either<Failure, Content>> get(Id id) {
    var content = contents.getById(id.getValue());
    return Results.succeed(content);
  }

  @Override
  public CompletableFuture<Either<Failure, Void>> delete(int id) {
    contents.deleteById(id);
    return Results.succeed(null);
  }

  @Override
  public CompletableFuture<Either<Failure, Collection<Content>>> getContentFeed(boolean approve) {
    return Results.succeed(new ArrayList<>(contents.findAllByApprove(approve)));
  }

  /**
   * ORM для доступа к данным контента в СУБД.
   */
  public interface Contents extends JpaRepository<ContentModel, Integer> {
    Collection<Content> findAllByApprove(boolean approve);
  }
}