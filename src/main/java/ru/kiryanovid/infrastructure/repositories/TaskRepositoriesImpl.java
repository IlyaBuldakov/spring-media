package ru.kiryanovid.infrastructure.repositories;

import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.entity.task.Task;
import ru.kiryanovid.domain.repositories.TaskRepositories;
import ru.kiryanovid.infrastructure.models.TaskModel;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Repository
public class TaskRepositoriesImpl implements TaskRepositories {

    @Autowired
    Tasks tasks;

    @Override
    public CompletableFuture<Either<Failure, Task>> create(Task task) {
        TaskModel taskModel = new TaskModel(task.getId(),
                task.getName(),
                task.getContentType(),
                task.getDescription(),
                task.getAuthor(),
                task.getExecutor(),
                task.getDateExpired());
        tasks.save(taskModel);
        return null;
    }

    @Override
    public Future<Either<Failure, Task>> update(Task task) {
        return null;
    }

    @Override
    public Future<Either<Failure, Void>> delete(Integer id) {
        return null;
    }

    @Override
    public CompletableFuture<Either<Failure, Task>> get(Integer id) {
        var task = tasks.findById(id).get();
        var task1 = Task.create(null,
                task.getName(),
                task.getContentType(),
                task.getDescription(),
                null,
                task.getAuthor(),
                task.getExecutor(),
                null,
                task.getDateExpired(),
                null,
                null,
                null).get();

        return CompletableFuture.completedFuture(Either.right(task1));
    }

    @Override
    public CompletableFuture<Either<Failure, Iterable<Task>>> getAll() {

        return null;

    }
    public interface Tasks extends JpaRepository<TaskModel, Integer>{

    }
}
