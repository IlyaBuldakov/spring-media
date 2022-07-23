package com.htc.infrastructure.repositories;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.repositories.ContentRepository;
import com.htc.infrastructure.models.ContentModel;
import io.vavr.control.Either;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
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
  public CompletableFuture<Either<Failure, Void>> delete(Id id) {
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, Collection<Content>>> findByQuery(
          Integer page,
          Integer count,
          String author,
          LocalDate date,
          Integer typeId) {
    Content.Type type = Content.Type.values()[typeId];
    var contentList = contents.findAllByAuthorNameAndDateCreatedAndType(
            author, date, type);

    if (contentList.isPresent()) {
      return CompletableFuture.completedFuture(
              Either.right(
                      new ArrayList<>(
                              contentList.get())));
    }

    return CompletableFuture.completedFuture(Either.left(NotFound.DEFAULT_MESSAGE));
  }

  @Override
  public CompletableFuture<Either<Failure, Collection<Content>>> findByTask(Id taskId) {

    var contentList = contents.findAllByParentTask(taskId);
    if (contentList.isPresent()) {
      return CompletableFuture.completedFuture(Either.right(new ArrayList<>(contentList.get())));
    }
    return CompletableFuture.completedFuture(Either.left(NotFound.DEFAULT_MESSAGE));
  }


  /**
   * ORM для доступа к данным медиаконтента в СУБД.
   */
  public interface Contents extends JpaRepository<ContentModel, Integer> {
    Optional<List<Content>> findAllByParentTask(Id taskId);

    //TODO: Запрос дожен сдержать строку - часть имени автора, а не его индентификатор.
    Optional<List<Content>> findAllByAuthorNameAndDateCreatedAndType(String author,
                                                                     LocalDate date,
                                                                     Content.Type type);

  }
}
