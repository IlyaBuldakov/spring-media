package ru.kiryanovid.application.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kiryanovid.application.dto.errors.InternalServerErrorDto;
import ru.kiryanovid.application.dto.task.TaskDto;
import ru.kiryanovid.application.dto.task.TaskListDto;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.entity.task.Task;
import ru.kiryanovid.domain.usecases.task.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
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
    public Iterable<TaskListDto> getAll() throws ExecutionException, InterruptedException {
        Iterable<Task> taskList = getAllTasks.execute(null)
                .get()
                .get();
        List<TaskListDto> dtoList = new ArrayList<>();
        for(Task task : taskList){
            dtoList.add(TaskListDto.mapToDto(task));
        }
        return dtoList;
    }
    @PostMapping
    public void create(@ModelAttribute("task") Task task){
        createTask.execute(task);
    }
    @GetMapping(path = "/{id}")
    public CompletableFuture<TaskDto> getTaskById(@PathVariable Integer id) throws ExecutionException, InterruptedException {
        var result = getTaskById.execute(id);
        if(result.get().isLeft()){
            var temp = result.get().getLeft();
            throw new InternalServerErrorDto(temp);
        }
        return CompletableFuture.completedFuture(new TaskDto(result.get().get()));
    }
    @PutMapping("/{id}")
    public void updateTask(@PathVariable Integer id) throws ExecutionException, InterruptedException {
        var task = getTaskById.execute(id).get().get();
        updateTask.execute(task);
    }
    @DeleteMapping("/{id}")
    public void deleteTaskById(@PathVariable Integer id){
        deleteTaskById.execute(id);
    }

}
