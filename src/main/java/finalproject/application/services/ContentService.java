package finalproject.application.services;

import finalproject.domain.entities.content.Content;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.task.Task;
import finalproject.domain.entities.user.User;
import io.vavr.control.Either;

import java.util.List;
import java.util.concurrent.Future;

public interface ContentService {
  Future<Either<Failure, Integer>> addContentToTask (Content content, User user);
  Future<Either<Failure, Void>> deleteContentById (int id);
  Future<Either<Failure, Content>> getContentById (int id);
  Future<Either<Failure, List<Content>>> getAllContentRelatedToTask (int taskId);
  Future<Either<Failure, Void>> setContentStatusToPublished (Content content, User user);
}
