package com.htc.infrastructure.repositories.repositoriesimplementation;

import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.Type;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.file.File;
import com.htc.domain.entities.task.Status;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.EntityName;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.repositories.TaskRepository;
import com.htc.infrastructure.models.comment.CommentModel;
import com.htc.infrastructure.models.content.ContentModel;
import com.htc.infrastructure.models.file.FileModel;
import com.htc.infrastructure.models.task.TaskModel;
import com.htc.infrastructure.models.user.UserModel;
import com.htc.infrastructure.repositories.Tasks;
import com.htc.utility.EitherHelper;
import io.vavr.control.Either;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория задачи.
 */
@Repository
@AllArgsConstructor
public class TaskRepositoryImplementation implements TaskRepository {
  Tasks tasks;

  @Override
  public CompletableFuture<Either<Failure, Task>> add(EntityName name, Type type,
                                                      String description, User author,
                                                      User executor, DateCreated dateCreated,
                                                      DateCreated dateExpired) {
    var taskModel = new TaskModel(
            0L,
            name.getValue(),
            type.getName(),
            description,
            null,
            (UserModel) author,
            (UserModel) executor,
            dateCreated.getValue(),
            dateExpired.getValue(),
            null,
            null,
            null);
    return EitherHelper.goodRight(tasks.save(taskModel));
  }

  @Override
  public CompletableFuture<Either<Failure, Task>> get(Id id) {
    //TODO реализовать проверку на наличие в базе записи
    return tasks.findById((long) id.getValue()).thenApply(Either::right);
  }

  @Override
  public CompletableFuture<Either<Failure, List<Task>>> getAll() {
    var taskList = tasks.findAll().stream().map(taskModel -> (Task) taskModel).toList();
    return EitherHelper.goodRight(taskList);
  }

  @Override
  public CompletableFuture<Either<Failure, Task>> update(Id id, EntityName name, Type type,
                                                         String description, File file,
                                                         User author, User executor,
                                                         DateCreated dateCreated,
                                                         DateCreated dateExpired, Content content,
                                                         Comment comment, Status status) {
    var taskModel = new TaskModel(
            id.getValue(),
            name.getValue(),
            type.getName(),
            description,
            (FileModel) file,
            (UserModel) author,
            (UserModel) executor,
            dateCreated.getValue(),
            dateExpired.getValue(),
            (ContentModel) content,
            (CommentModel) comment,
            status.getName());
    return EitherHelper.goodRight(tasks.save(taskModel));
  }

  @Override
  public CompletableFuture<Either<Failure, Void>> delete(Id id) {
    tasks.deleteById(id.getValue());
    return EitherHelper.goodRight(null);
  }
}
