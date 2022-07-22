package com.htc.application.services;

import com.htc.application.dto.task.TaskRequest;
import com.htc.application.dto.task.TaskResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface TasksService {

    CompletableFuture<List<TaskResponse>> getAll();
    CompletableFuture<TaskResponse> getById(String id);

    /**
     * Создание задачи.
     *
     * @param task {@link TaskRequest Представление} задачи (запрос).
     * @return void.
     */
    CompletableFuture<Void> create(TaskRequest task);

    /**
     * Обновление задачи.
     *
     * @param task {@link TaskRequest Представление} задачи (запрос).
     * @param id Идентификатор задачи.
     * @return void.
     */
    CompletableFuture<Void> update(TaskRequest task, String id);

    /**
     * Удаление задачи по идентификатору.
     *
     * @param id Идентификатор задачи.
     * @return void.
     */
    CompletableFuture<Void> delete(String id);
}
