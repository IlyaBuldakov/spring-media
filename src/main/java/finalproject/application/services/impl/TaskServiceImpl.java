package finalproject.application.services.impl;

import finalproject.application.services.TaskService;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.task.Task;
import finalproject.infrastructure.repositories.TaskRepository;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {


  private final TaskRepository taskRepository;




  @Async
  @Override
  public CompletableFuture<Either<Failure, Task>> createNewTask(Task task) {


    return CompletableFuture.completedFuture(Either.right(taskRepository.save(task)));
  }

  @Override
  public CompletableFuture<Either<Failure, Task>> editTask(Task task, int id) {
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, Void>> deleteTask(Task task, int id) {
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, Task>> getTaskById(int id) {
    if(!taskRepository.existsById(id)) {
      return CompletableFuture.completedFuture(Either.left(new Failure(Failure.Messages.ENTITY_NOT_FOUND)));
    }
    if (taskRepository.findById(id).isPresent()) {
    return CompletableFuture.completedFuture(Either.right(taskRepository.findById(id).get()));}
    else return CompletableFuture.completedFuture(Either.left(new Failure(Failure.Messages.INTERNAL_SERVER_ERROR)));
  }

  @Override
  public CompletableFuture<Either<Failure, List<Task>>> getAllTasks() {
    return null;
  }


}

