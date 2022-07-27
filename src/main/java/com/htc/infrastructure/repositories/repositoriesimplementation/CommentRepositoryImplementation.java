package com.htc.infrastructure.repositories.repositoriesimplementation;

import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.repositories.CommentRepository;
import com.htc.infrastructure.models.comment.CommentModel;
import com.htc.infrastructure.models.user.UserModel;
import com.htc.infrastructure.repositories.Comments;
import com.htc.utility.EitherHelper;
import io.vavr.control.Either;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория комментария.
 */
@Repository
@AllArgsConstructor
public class CommentRepositoryImplementation implements CommentRepository {
  Comments comments;

  @Override
  public CompletableFuture<Either<Failure, Comment>> add(DateCreated dateCreated,
                                                         User author,
                                                         String message,
                                                         int[] content) {
    var commentModel = new CommentModel(
            0L,
            dateCreated.getValue(),
            (UserModel) author,
            message,
            Arrays.toString(content)
    );
    return EitherHelper.goodRight(comments.save(commentModel));
  }

  @Override
  public CompletableFuture<Either<Failure, Comment>> get(Id id) {
    return comments.findById((long) id.getValue()).thenApply(Either::right);
  }

  @Override
  public CompletableFuture<Either<Failure, Void>> delete(Id id) {
    comments.deleteById(id.getValue());
    return EitherHelper.goodRight(null);
  }
}
