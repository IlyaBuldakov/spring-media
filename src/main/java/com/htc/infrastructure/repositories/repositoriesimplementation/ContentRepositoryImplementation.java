package com.htc.infrastructure.repositories.repositoriesimplementation;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.Type;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.file.File;
import com.htc.domain.entities.file.Format;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.EntityName;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.entities.utility.parameters.file.FileUrlPath;
import com.htc.domain.repositories.ContentRepository;
import com.htc.infrastructure.models.content.ContentModel;
import com.htc.infrastructure.models.file.FileModel;
import com.htc.infrastructure.models.task.TaskModel;
import com.htc.infrastructure.models.user.UserModel;
import com.htc.infrastructure.repositories.Contents;
import com.htc.utility.EitherHelper;
import io.vavr.control.Either;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория контента.
 */
@Repository
@AllArgsConstructor
public class ContentRepositoryImplementation implements ContentRepository {
  Contents contents;

  @Override
  public CompletableFuture<Either<Failure, Content>> add(EntityName name, Type type,
                                                         DateCreated dateCreated, User author,
                                                         Format format, FileUrlPath previewPath,
                                                         File file, Task task) {
    var contentModel = new ContentModel(
            0L,
            type.getName(),
            name.getValue(),
            dateCreated.getValue(),
            (UserModel) author,
            format.getName(),
            previewPath.getValue(),
            (FileModel) file,
            (TaskModel) task);
    return EitherHelper.goodRight(contents.save(contentModel));
  }

  @Override
  public CompletableFuture<Either<Failure, Content>> get(Id id) {
    return contents.findById((long) id.getValue()).thenApply(Either::right);
  }

  @Override
  public CompletableFuture<Either<Failure, List<Content>>> getAll() {
    var contentList = contents.findAll().stream()
            .map(contentModel -> (Content) contentModel).toList();
    return EitherHelper.goodRight(contentList);
  }

  @Override
  public CompletableFuture<Either<Failure, Content>> change(Id id, EntityName name, Type type,
                                                            DateCreated dateCreated, User author,
                                                            Format format, FileUrlPath previewPath,
                                                            File file, Task task) {
    var contentModel = new ContentModel(
            id.getValue(),
            type.getName(),
            name.getValue(),
            dateCreated.getValue(),
            (UserModel) author,
            format.getName(),
            previewPath.getValue(),
            (FileModel) file,
            (TaskModel) task);
    return EitherHelper.goodRight(contents.save(contentModel));
  }

  @Override
  public CompletableFuture<Either<Failure, Void>> delete(Id id) {
    contents.deleteById(id.getValue());
    return EitherHelper.goodRight(null);
  }
}
