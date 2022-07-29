package finalproject.application.services.impl;


import finalproject.application.services.AuthService;
import finalproject.application.services.TaskService;
import finalproject.domain.entities.content.Content;
import finalproject.domain.entities.failures.*;
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

  private final AuthService authService;






  @Async
  @Override
  public CompletableFuture<Either<Failure, Task>> createNewTask(Task task) {
    User authorizedUser = userRepository.findById(authService.getId()).get();
    if (authorizedUser.getRole() != Role.ADMIN && !task.getAuthor().equals(authorizedUser)) {
      return CompletableFuture.completedFuture(
              Either.left(new NotAuthorized(Messages.NOT_ENOUGH_AUTHORITY)));
    }
    return CompletableFuture.completedFuture(Either.right(taskRepository.save(task)));
  }

  @Override
  public CompletableFuture<Either<Failure, Task>> editTask(Task task, int id) {
    if (!taskRepository.existsById(id)) {
      return CompletableFuture.completedFuture(
              Either.left(new NotFound(Messages.TASK_NOT_FOUND)));
    }

    Task oldTask = taskRepository.findById(id).get();
    User authorizedUser = userRepository.findById(authService.getId()).get();
    if (authorizedUser.getRole() != Role.ADMIN
            && (task.getTaskStatus() != oldTask.getTaskStatus()
              || !task.getAuthor().equals(authorizedUser)))
    {
      return CompletableFuture.completedFuture(
              Either.left(new NotAuthorized(Messages.NOT_ENOUGH_AUTHORITY)));
    }
    task.setDateCreated(oldTask.getDateCreated());
    task.setId(oldTask.getId());
    if (task.getTaskStatus() == TaskStatus.APPROVED && oldTask.getTaskStatus() != TaskStatus.APPROVED) {
      for (Content content : contentRepository.findByTaskId(task.getId())) {
        content.setIsPublished(true);
        contentRepository.save(content);
      }

    }

    return CompletableFuture.completedFuture(Either.right(taskRepository.save(task)));
  }

  @Override
  public CompletableFuture<Either<Failure, Void>> deleteTask(Task task, int id) {
    List<String> problems = new ArrayList<>();
    if (id <= 1) {
      problems.add("id");
      return CompletableFuture.completedFuture(Either.left(
              new BadRequest(Messages.INVALID_VALUES, problems)));
    }
    if (!taskRepository.existsById(id)) {
      return CompletableFuture.completedFuture(Either.left(
              new NotFound(Messages.USER_NOT_FOUND)));
    }
    User authorizedUser = userRepository.findById(authService.getId()).get();
    if (authorizedUser.getRole() == Role.ADMIN || authorizedUser.equals(task.getAuthor()))
    {
    taskRepository.deleteById(id);
    return CompletableFuture.completedFuture(Either.right(null));}
    return CompletableFuture.completedFuture(
            Either.left(new NotAuthorized(Messages.NOT_ENOUGH_AUTHORITY)));

  }

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

  @Override
  public CompletableFuture<Either<Failure, List<Task>>> getAllTasks() {
    return null;
  }


}

