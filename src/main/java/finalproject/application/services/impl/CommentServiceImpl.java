package finalproject.application.services.impl;

import finalproject.application.services.CommentService;
import finalproject.domain.entities.Comment;
import finalproject.domain.entities.failures.*;
import finalproject.domain.entities.notifications.Notification;
import finalproject.domain.entities.notifications.NotificationType;
import finalproject.domain.entities.task.Task;
import finalproject.domain.entities.user.Role;
import finalproject.domain.entities.user.User;
import finalproject.infrastructure.repositories.CommentRepository;
import finalproject.infrastructure.repositories.NotificationsRepository;
import finalproject.infrastructure.repositories.TaskRepository;
import finalproject.infrastructure.repositories.UserRepository;
import finalproject.utils.validators.Validators;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

  TaskRepository taskRepository;
  UserRepository userRepository;

  NotificationsRepository notificationsRepository;
  CommentRepository commentRepository;

  @Async
  @Override
  public CompletableFuture<Either<Failure, Comment>> postCommentToTask(int userId, int taskId, String commentString) {
    Validators validators = new Validators();
    validators.validateId(userId, "userId");
    validators.validateId(userId, "taskId");
    validators.validateNonNullString(commentString, "comment");
    if (validators.getProblems().size() > 0)
      return CompletableFuture.completedFuture(Either.left(
              new BadRequest(Messages.INVALID_VALUES, validators.getProblems())));
    if (!taskRepository.existsById(taskId)) {
      return CompletableFuture.completedFuture(Either.left(
              new NotFound(Messages.TASK_NOT_FOUND)));
    }
    if (!userRepository.existsById(userId)) {
      return CompletableFuture.completedFuture(Either.left(
              new NotFound(Messages.USER_NOT_FOUND)));
    }

    Comment comment = new Comment(taskRepository.findById(taskId).get(),
            userRepository.findById(userId).get(),
            commentString,
            LocalDateTime.now()
           );
    NotificationType noteType = NotificationType.COMMENT;
    Notification notification = new Notification(noteType, LocalDateTime.now(),
            userRepository.findById(userId).get(), taskRepository.findById(taskId).get(), Notification.Note.ADD);
    notificationsRepository.save(notification);

    return CompletableFuture.completedFuture(Either.right(commentRepository.save(comment)));
  }


@Async
  @Override
  public CompletableFuture<Either<Failure, Void>> deleteCommentById(int id, int auth) {

    User authorizedUser = userRepository.findById(auth).get();
    List<String> problems = new ArrayList<>();
    if (id < 1) {
      problems.add("id");
      return CompletableFuture.completedFuture(Either.left(
              new BadRequest(Messages.INVALID_VALUES, problems)));}
    if (!commentRepository.existsById(id)) {
      return CompletableFuture.completedFuture(Either.left(
              new NotFound(Messages.COMMENT_NOT_FOUND)));
    }
    Comment comment = commentRepository.findById(id).get();
    User user = comment.getUser();
    if (authorizedUser.getRole() != Role.ADMIN && !user.equals(authorizedUser)) {
      return CompletableFuture.completedFuture(
              Either.left(new NotAuthorized(Messages.NOT_ENOUGH_AUTHORITY)));
    }
    comment.getTask().getComments().remove(comment);
    user.getComments().remove(comment);
    commentRepository.deleteById(id);
    return CompletableFuture.completedFuture(Either.right(null));
  }

}
