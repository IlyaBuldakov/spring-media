package ru.kiryanovid.infrastructure.repositories;

import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.entity.task.Task;
import ru.kiryanovid.domain.repositories.TaskRepositories;
import ru.kiryanovid.infrastructure.models.TaskModel;
import ru.kiryanovid.infrastructure.models.UserModel;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Repository
public class TaskRepositoriesImpl implements TaskRepositories {

    @Autowired
    Tasks tasks;

    @Override
    public CompletableFuture<Either<Failure, Task>> create(Task task) {
        var x = task.getExecutor();
        var taskModel = new TaskModel(task.getId(),
                task.getName(),
                task.getContentType(),
                task.getDescription(),
                null,
                new UserModel(task.getAuthor()),
                new UserModel(task.getExecutor()),
                null,
                task.getDateExpired(),
                null,
                null,
                null
                );
        tasks.save(taskModel);
        return null;
    }

    @Override
    public Future<Either<Failure, Task>> update(Task task) {

        return null;
    }

    @Override
    public Future<Either<Failure, Void>> delete(Integer id) {
        tasks.deleteById(id);
        return null;
    }

    @Override
    public CompletableFuture<Either<Failure, Task>> get(Integer id) {
        var taskModel = tasks.findById(id).orElseThrow();
        var task = Task.create(null,
                taskModel.getName(),
                taskModel.getContentType(),
                taskModel.getDescription(),
                null,
                taskModel.getId(),
                taskModel.getId(),
                null,
                taskModel.getDateExpired(),
                null,
                null,
                null).get();

        return CompletableFuture.completedFuture(Either.right(task));
    }

    @Override
    public CompletableFuture<Either<Failure, Iterable<Task>>> getAll() {
        var taskList = tasks.findAll().stream().map(taskModel -> Task.create(null,
                taskModel.getName(),
                taskModel.getContentType(),
                taskModel.getDescription(),
                null,
                taskModel.getId(),
                taskModel.getId(),
                null,
                taskModel.getDateExpired(),
                null,
                null,
                null).get()).toList();
        return CompletableFuture.completedFuture(Either.right(taskList));

    }
    public interface Tasks extends JpaRepository<TaskModel, Integer>{

    }
}
