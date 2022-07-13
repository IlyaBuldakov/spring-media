package finalproject.application.services;

import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.task.Task;
import finalproject.domain.entities.user.User;
import io.vavr.control.Either;

import java.util.List;
import java.util.concurrent.Future;

public interface TaskService {

  Future<Either<Failure, Task>> createNewTask(Task task);
  Future<Either<Failure, Task>> editTask(Task task, int id);
  Future<Either<Failure, Void>> deleteTask(Task task, int id);
  Future<Either<Failure, Task>> getTaskById(int id);
  Future<Either<Failure, List<Task>>> getAllTasks();
  /**
   * статус задачи меняется с FEEDBACK на APPROVED, статус контента меняется с NON-PUBLISHED на PUBLISHED
   */
  Future<Either<Failure, Task>> approveTask(Task task);




}
