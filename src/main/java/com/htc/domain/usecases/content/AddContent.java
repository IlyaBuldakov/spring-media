package com.htc.domain.usecases.content;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.Type;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValueParam;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.file.File;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.entities.utility.parameters.file.FileName;
import com.htc.domain.entities.utility.parameters.file.FileUrlPath;
import com.htc.domain.repositories.ContentRepository;
import com.htc.domain.repositories.FileRepository;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utility.EitherHelper;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий добавления нового контента.
 */
//@Component
@AllArgsConstructor
public final class AddContent implements UseCase<AddContent.Params, Content> {
  /**
   * Параметры сценария добавления контента.
   *
   * @param name наименование контента
   * @param nameKey ключ наименования контента
   * @param type тип контента
   * @param typeKey ключ типа контента
   * @param authorId идентификатор автора контента
   * @param authorKey ключ идентификатора автора контента
   * @param previewPath превью контента
   * @param previewPathKey ключ превью контента
   * @param fileId идентификатор файла, относящийся к контенту
   * @param fileKey ключ идентификатора файла, относящийся к контенту
   */
  public record Params(String name, String nameKey,
                       Type type, String typeKey,
                       Long authorId, String authorKey,
                       String previewPath, String previewPathKey,
                       Long fileId, String fileKey) {}

  private final ContentRepository repository;
  private final UserRepository userRepository;
  private final FileRepository fileRepository;

  @Override
  public CompletableFuture<Either<Failure, Content>> execute(Params params) {
    var failure = new InvalidValues();
    //TODO возможно filename переименовать в EntityName
    var name = FileName.create(params.name());
    if (name.isLeft()) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_NAME, params.nameKey);
    }
    User user = null;
    try {
      user = userRepository.get(Id.create(params.authorId()).get()).get().get();
    } catch (InterruptedException | ExecutionException e) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_ID, "user not found");
    }
    var previewPath = FileUrlPath.create(params.previewPath());
    if (previewPath.isLeft()) {
      failure.getValues().put(InvalidValueParam.INVALID_FILE_URL_PATH, params.previewPathKey);
    }
    File file = null;
    try {
      file = fileRepository.get(Id.create(params.authorId()).get()).get().get();
    } catch (InterruptedException | ExecutionException e) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_ID, "file not found");
    }
    return failure.getValues().size() == 0
            ? repository.add(name.get(), params.type(), user, previewPath.get(), file)
            : EitherHelper.badLeft(failure);
  }
}