package finalproject.application.services.impl;



import finalproject.application.services.TaskService;
import finalproject.domain.entities.content.Content;
import finalproject.domain.entities.failures.BadRequest;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.failures.InternalServerError;
import finalproject.domain.entities.failures.Messages;
import finalproject.domain.entities.failures.NotAuthorized;
import finalproject.domain.entities.failures.NotFound;
import finalproject.domain.entities.task.Task;
import finalproject.domain.entities.task.TaskStatus;
import finalproject.domain.entities.user.Role;
import finalproject.domain.entities.user.User;
import finalproject.infrastructure.repositories.ContentRepository;
import finalproject.infrastructure.repositories.TaskRepository;
import finalproject.infrastructure.repositories.UserRepository;
import io.vavr.control.Either;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса для работы с задачами.
 */
@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {


  private final TaskRepository taskRepository;

  private final UserRepository userRepository;
  private final ContentRepository contentRepository;








  @Async
  @Override
  public CompletableFuture<Either<Failure, Task>> createNewTask(Task task, int authorizedUserId) {
    User authorizedUser = userRepository.findById(authorizedUserId).get();
    if (authorizedUser.getRole() != Role.ADMIN && !task.getAuthor().equals(authorizedUser)) {
      return CompletableFuture.completedFuture(
              Either.left(new NotAuthorized(Messages.NOT_ENOUGH_AUTHORITY)));
    }
    return CompletableFuture.completedFuture(Either.right(taskRepository.save(task)));
  }

  @Async
  @Override
  public CompletableFuture<Either<Failure, Task>> editTask(Task task, int id, int auth) {
    if (!taskRepository.existsById(id)) {
      return CompletableFuture.completedFuture(
              Either.left(new NotFound(Messages.TASK_NOT_FOUND)));
    }

    Task oldTask = taskRepository.findById(id).get();
    User authorizedUser = userRepository.findById(auth).get();
    if (authorizedUser.getRole() != Role.ADMIN
            && (task.getTaskStatus() != oldTask.getTaskStatus()
              || !task.getAuthor().equals(authorizedUser))) {
      return CompletableFuture.completedFuture(
              Either.left(new NotAuthorized(Messages.NOT_ENOUGH_AUTHORITY)));
    }
    task.setDateCreated(oldTask.getDateCreated());
    task.setId(oldTask.getId());
    if (task.getTaskStatus() == TaskStatus.APPROVED
            && oldTask.getTaskStatus() != TaskStatus.APPROVED) {
      for (Content content : contentRepository.findByTaskId(task.getId())) {
        content.setIsPublished(true);
        contentRepository.save(content);
      }

    }

    return CompletableFuture.completedFuture(Either.right(taskRepository.save(task)));
  }

  @Async
  @Override
  public CompletableFuture<Either<Failure, Void>> deleteTask(int id, int auth) {
    List<String> problems = new ArrayList<>();
    if (id < 1) {
      problems.add("id");
      return CompletableFuture.completedFuture(Either.left(
              new BadRequest(Messages.INVALID_VALUES, problems)));
    }
    if (!taskRepository.existsById(id)) {
      return CompletableFuture.completedFuture(Either.left(
              new NotFound(Messages.TASK_NOT_FOUND)));
    }
    Task task = taskRepository.findById(id).get();
    User authorizedUser = userRepository.findById(auth).get();
    if (authorizedUser.getRole() == Role.ADMIN || authorizedUser.equals(task.getAuthor())) {
      for (Content content : contentRepository.findByTaskId(task.getId())) {
        content.setTask(null);
        contentRepository.save(content);
      }
      taskRepository.deleteById(id);
      return CompletableFuture.completedFuture(Either.right(null));
    }
    return CompletableFuture.completedFuture(
              Either.left(new NotAuthorized(Messages.NOT_ENOUGH_AUTHORITY)));

  }

  @Async
  @Override
  public CompletableFuture<Either<Failure, Task>> getTaskById(int id) {
    List<String> problems = new ArrayList<>();
    if (id < 1) {
      problems.add("id");
      return CompletableFuture.completedFuture(Either.left(
              new BadRequest(Messages.INVALID_VALUES, problems)));
    }
    if (!taskRepository.existsById(id)) {
      return CompletableFuture.completedFuture(
              Either.left(new NotFound(Messages.TASK_NOT_FOUND)));
    }
    if (taskRepository.findById(id).isPresent()) {
      return CompletableFuture.completedFuture(Either.right(taskRepository.findById(id).get()));
    } else {
      return CompletableFuture.completedFuture(
              Either.left(new InternalServerError(Messages.INTERNAL_SERVER_ERROR)));
    }
  }

  @Async
  @Override
  public CompletableFuture<List<Task>> getAllTasks(int auth) {
    User authorizedUser = userRepository.findById(auth).get();
    return switch (authorizedUser.getRole()) {
      case ADMIN -> CompletableFuture.completedFuture(
             taskRepository.findAll());
      case MANAGER -> CompletableFuture.completedFuture(
             taskRepository.findByAuthor(authorizedUser));
      case CONTENT_MAKER -> CompletableFuture.completedFuture(
              taskRepository.findByContentMaker(authorizedUser));
    };

  }


}

