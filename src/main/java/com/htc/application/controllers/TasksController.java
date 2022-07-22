package com.htc.application.controllers;

import com.htc.application.dto.task.TaskRequest;
import com.htc.application.dto.task.TaskResponse;
import com.htc.application.services.TasksService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tasks")
public class TasksController {

    TasksService tasksService;

    @GetMapping("/{id}")
    @Async
    public CompletableFuture<TaskResponse> getById(@PathVariable String id) {
        return tasksService.getById(id);
    }

    @GetMapping
    @Async
    public CompletableFuture<List<TaskResponse>> getAll() {
        return tasksService.getAll();
    }

    @PostMapping
    @Async
    public CompletableFuture<Void> create(@RequestBody TaskRequest taskRequest) {
        return tasksService.create(taskRequest);
    }

    @PutMapping("/{id}")
    @Async
    public CompletableFuture<Void> update(@RequestBody TaskRequest taskRequest, @PathVariable String id) {
        return tasksService.update(taskRequest, id);
    }

    @DeleteMapping("/{id}")
    @Async
    public CompletableFuture<Void> delete(@PathVariable("id") String id) {
        return tasksService.delete(id);
    }
}
