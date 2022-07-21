package com.htc.infrastructure.repositories;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.comments.Comment;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.entities.tasks.Task;
import com.htc.domain.repositories.CommentRepository;
import com.htc.infrastructure.models.CommentModel;
import com.htc.infrastructure.models.TaskModel;
import com.htc.infrastructure.models.UserModel;
import io.vavr.control.Either;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория комментария.
 */
@Repository
@AllArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

  Comments comments;

  TaskRepositoryImpl taskRepository;

  UserRepositoryImpl userRepository;

  @Override
  public CompletableFuture<Either<Failure, Comment>> create(
          Id userId,
          Id taskId,
          Comment.Message message) {
    var user = userRepository.get(userId).join();
    var task = taskRepository.get(taskId).join();
    if (user.isLeft()) {
      return CompletableFuture.completedFuture(Either.left(NotFound.DEFAULT_MESSAGE));
    }
    if (task.isLeft()) {
      return CompletableFuture.completedFuture(Either.left(NotFound.DEFAULT_MESSAGE));
    }

    var comment = new CommentModel((UserModel) user.get(), (TaskModel) task.get(), message);
    return CompletableFuture.completedFuture(Either.right(comments.save(comment)));
  }

  @Override
  public CompletableFuture<Either<Failure, Comment>> update(Comment comment) {
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, Void>> delete(Id id) {
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, Collection<Comment>>> getAllByTask(Task task) {
    return null;
  }

  /**
   * ORM для доступа к данным пользователей в СУБД.
   */
  public interface Comments extends JpaRepository<CommentModel, Integer> {

  }
}
