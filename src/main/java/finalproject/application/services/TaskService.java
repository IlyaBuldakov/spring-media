package finalproject.application.services;

import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.task.Task;
import finalproject.domain.entities.user.User;
import io.vavr.control.Either;

import java.util.List;
import java.util.concurrent.Future;

public interface TaskService {

  Future<Either<Failure, Task>> createNewTask(Task task, User user);
  Future<Either<Failure, Task>> editTask(Task task, User user);
  Future<Either<Failure, Void>> deleteTask(Task task);
  Future<Either<Failure, Task>> getTaskById(int id);
  Future<Either<Failure, List<Task>>> getAllTasks(User user);
  Future<Either<Failure, List<Task>>> getTasksByQuery(String query, User user);

  /**
   * статус задачи меняется с FEEDBACK на APPROVED, статус контента меняется с NON-PUBLISHED на PUBLISHED
   */
  Future<Either<Failure, Task>> approveTask(Task task);




}
