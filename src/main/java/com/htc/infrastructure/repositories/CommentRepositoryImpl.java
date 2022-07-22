package com.htc.infrastructure.repositories;

import com.htc.domain.entities.Comment;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.CommentRepository;
import com.htc.infrastructure.models.CommentModel;
import com.htc.utility.Results;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория пользователей.
 */
@Repository
public class CommentRepositoryImpl implements CommentRepository {
  @Autowired
  Comments comments;

  @Override
  public CompletableFuture<Either<Failure, Comment>> create(
      LocalDateTime dateCreateComment,
      int userId,
      int taskId,
      String commentMessage
  ) {
    var comment = new CommentModel(
        dateCreateComment,
        userId,
        taskId,
        commentMessage);
    return Results.succeed(comments.save(comment));
  }

  @Override
  public CompletableFuture<Either<Failure, Collection<Comment>>> getCommentsByTaskId(int taskId) {

    return Results.succeed(comments.findAllByTaskId(taskId));
  }

  /**
   * ORM для доступа к данным пользователей в СУБД.
   */
  public interface Comments extends JpaRepository<CommentModel, Integer> {
    Collection<Comment> findAllByTaskId(int taskId);
  }
}
