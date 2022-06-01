package ru.kiryanovid.application.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kiryanovid.domain.entity.task.Task;
import ru.kiryanovid.domain.usecases.task.GetAllTasks;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path = "api/tasks")
@AllArgsConstructor
public class TaskController {
    private final GetAllTasks getAllTasks;

    @GetMapping
    public Iterable<Task> getAll() throws ExecutionException, InterruptedException {
        return getAllTasks.execute(null)
                .get()
                .get();
    }
}
