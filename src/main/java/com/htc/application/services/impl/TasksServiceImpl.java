package com.htc.application.services.impl;

import com.htc.application.dto.task.TaskRequest;
import com.htc.application.dto.task.TaskResponse;
import com.htc.application.services.ExceptionDtoResolver;
import com.htc.application.services.TasksService;
import com.htc.domain.usecases.task.CreateTask;
import com.htc.domain.usecases.task.DeleteTask;
import com.htc.domain.usecases.task.GetAllTasks;
import com.htc.domain.usecases.task.GetTaskById;
import com.htc.domain.usecases.task.UpdateTask;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@Service
public class TasksServiceImpl implements TasksService {

    CreateTask createTask;
    GetAllTasks getAllTasks;
    GetTaskById getTaskById;
    UpdateTask updateTask;
    DeleteTask deleteTask;

    @Override
    public CompletableFuture<List<TaskResponse>> getAll() {
        return getAllTasks.execute()
                .thenApply(either ->
                        either.map(list -> list.parallelStream()
                                        .map(TaskResponse::new)
                                        .toList())
                                .getOrElseThrow(ExceptionDtoResolver::resolve));
    }

    @Override
    public CompletableFuture<TaskResponse> getById(String id) {
        return getTaskById.execute(id)
                .thenApply(either ->
                        either.map(TaskResponse::new).getOrElseThrow(ExceptionDtoResolver::resolve));
    }

    @Override
    public void create(TaskRequest task) {
        createTask.execute(
                task.getName(), task.getType(), task.getDescription(),
                task.getAuthor(), task.getExecutor(), task.getDateExpired());
    }

    @Override
    public void update(TaskRequest user, String id) {
        updateTask.execute(id, user.getName(), user.getType(), user.getDescription(),
                           user.getAuthor(), user.getExecutor(), user.getDateExpired());
    }

    @Override
    public void delete(String id) {
        deleteTask.execute(id);
    }
}
