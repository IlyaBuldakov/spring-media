package finalproject.application.services.impl;

import finalproject.application.services.TaskService;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.task.Task;
import finalproject.domain.repositories.*;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

  private final UserRepository userRepository;
  private final TaskRepository taskRepository;
  private final ContentRepository contentRepository;
  private final CommentRepository commentRepository;
  private final FileRepository fileRepository;



  @Override
  public Future<Either<Failure, Task>> createNewTask(Task task) {


    return CompletableFuture.completedFuture(Either.right(taskRepository.save(task));
  }

  @Override
  public Future<Either<Failure, Task>> editTask(Task task, int id) {
    return null;
  }

  @Override
  public Future<Either<Failure, Void>> deleteTask(Task task, int id) {
    return null;
  }

  @Override
  public Future<Either<Failure, Task>> getTaskById(int id) {
    return null;
  }

  @Override
  public Future<Either<Failure, List<Task>>> getAllTasks() {
    return null;
  }

  @Override
  public Future<Either<Failure, Task>> approveTask(Task task) {
    return null;
  }
}

