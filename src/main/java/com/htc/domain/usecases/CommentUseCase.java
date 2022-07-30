package com.htc.domain.usecases;

import com.htc.domain.entities.Comment;
import com.htc.domain.entities.Task;
import com.htc.domain.entities.User;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.CommentRepository;
import com.htc.domain.repositories.UserRepository;
import io.vavr.control.Either;
import java.util.Collection;
import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.NonNull;

/**
 * Сценарии использования комментария.
 */
public class CommentUseCase {
  /**
   * Сценарий создания комментария.
   */
  public static class Create extends BaseUseCase<Create.Params, Comment> {

    private final CommentRepository repository;

    @Override
    protected Set<User.Role> getPermittedRoles() {
      return null;
    }

    @Override
    public Either<Failure, Comment> execute(Params params) {

      return Either.right(repository.create(
              params.user,
              params.task,
              params.message));
    }

    public Create(
            @NotNull CommentRepository repository,
            @NonNull UserRepository userRepository) {
      super(userRepository);
      this.repository = repository;
    }

    /**
     * Параметры выполнения сценария создания комментария.
     *
     * @param user Пользователь - автор комментария.
     * @param task Задача под которой остален комментарий.
     * @param message Тест комментария.
     */
    public record Params(
            User user,
            Task task,
            Comment.Message message) {
    }
  }

  /**
   * Сценарий удаления комментария по его идентификатору.
   */
  public static final class DeleteById extends BaseUseCase<Id, Void> {
    private final CommentRepository repository;

    @Override
    protected Set<User.Role> getPermittedRoles() {
      return null;
    }

    @Override
    public Either<Failure, Void> execute(@NonNull Id id) {
      if (repository.exists(id)) {
        return Either.left(new UserUseCase.NotFound());
      }
      repository.delete(id);
      return Either.right(null);
    }

    public DeleteById(
            @NotNull CommentRepository repository,
            @NonNull UserRepository userRepository) {
      super(userRepository);
      this.repository = repository;
    }
  }

  /**
   * Сценарий получения комментария по ее идентификатору.
   */
  public static final class GetAllByTask extends BaseUseCase<Task, Collection<Comment>> {
    private final CommentRepository repository;

    @Override
    protected Set<User.Role> getPermittedRoles() {
      return null;
    }

    @Override
    public Either<Failure, Collection<Comment>> execute(@NonNull Task task) {
      return Either.right(repository.getAllByTask(task));
    }

    public GetAllByTask(
            @NotNull CommentRepository repository,
            @NonNull UserRepository userRepository) {
      super(userRepository);
      this.repository = repository;
    }
  }
}
