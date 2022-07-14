package finalproject.application.services;

import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.task.Task;
import io.vavr.control.Either;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface TaskService {

  CompletableFuture<Either<Failure, Task>> createNewTask(Task task);
  CompletableFuture<Either<Failure, Task>> editTask(Task task, int id);
  CompletableFuture<Either<Failure, Void>> deleteTask(Task task, int id);
  CompletableFuture<Either<Failure, Task>> getTaskById(int id);
  CompletableFuture<Either<Failure, List<Task>>> getAllTasks();
  /**
   * статус задачи меняется с FEEDBACK на APPROVED, статус контента меняется с NON-PUBLISHED на PUBLISHED
   */
  CompletableFuture<Either<Failure, Task>> approveTask(Task task);




}
