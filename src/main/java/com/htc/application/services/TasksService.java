package com.htc.application.services;

import com.htc.application.dto.task.TaskRequest;
import com.htc.application.dto.task.TaskResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface TasksService {

    CompletableFuture<List<TaskResponse>> getAll();
    CompletableFuture<TaskResponse> getById(String id);
    void create(TaskRequest task);
    void update(TaskRequest task, String id);
    void delete(String id);
}
