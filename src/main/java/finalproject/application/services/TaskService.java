package finalproject.application.services;

import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.task.Task;
import io.vavr.control.Either;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Интерфейс задач.
 */
public interface TaskService {

  CompletableFuture<Either<Failure, Task>> createNewTask(Task task);

  CompletableFuture<Either<Failure, Task>> editTask(Task task, int id);

  CompletableFuture<Either<Failure, Void>> deleteTask(Task task, int id);

  CompletableFuture<Either<Failure, Task>> getTaskById(int id);

  CompletableFuture<Either<Failure, List<Task>>> getAllTasks();



}
