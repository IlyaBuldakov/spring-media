package ru.kiryanovid.application.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kiryanovid.domain.entity.task.Task;
import ru.kiryanovid.domain.usecases.task.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path = "api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final GetAllTasks getAllTasks;
    private final CreateTask createTask;
    private final GetTaskById getTaskById;
    private final UpdateTask updateTask;
    private final DeleteTaskById deleteTaskById;

    @GetMapping
    public Iterable<Task> getAll() throws ExecutionException, InterruptedException {
        return getAllTasks.execute(null)
                .get()
                .get();
    }
    @PostMapping
    public void create(@ModelAttribute("task") Task task){
        createTask.execute(task);
    }
    @GetMapping("/{id}")
    public Task getTaskById(int id) throws ExecutionException, InterruptedException {
        return getTaskById.execute(id)
                .get()
                .get();
    }
    @PutMapping("/{id}")
    public void updateTask(int id) throws ExecutionException, InterruptedException {
        var task = getTaskById.execute(id).get().get();
        updateTask.execute(task);
    }
    @GetMapping("/{id}")
    public void deleteTaskById(int id){
        deleteTaskById.execute(id);
    }

}
