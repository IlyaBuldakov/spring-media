package finalproject.application.services;

import finalproject.domain.entities.Comment;
import finalproject.domain.entities.content.Content;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.user.User;
import io.vavr.control.Either;

import java.util.List;
import java.util.concurrent.Future;

public interface CommentService {
  Future<Either<Failure, Integer>> PostCommentToTask (Content content, User user);
  Future<Either<Failure, List<Comment>>> getAllCommentsRelatedToTask (int taskId);
}
