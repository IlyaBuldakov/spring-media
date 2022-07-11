package ru.kiryanovid.application.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kiryanovid.application.dto.errors.BadRequestDto;
import ru.kiryanovid.application.dto.errors.InternalServerErrorDto;
import ru.kiryanovid.application.dto.errors.NotFoundDto;
import ru.kiryanovid.application.dto.task.TaskDto;
import ru.kiryanovid.application.dto.task.TaskListDto;
import ru.kiryanovid.application.dto.task.TaskRequestDto;
import ru.kiryanovid.domain.entity.errors.InvalidValue;
import ru.kiryanovid.domain.entity.task.Task;
import ru.kiryanovid.domain.usecases.task.*;
import ru.kiryanovid.domain.usecases.user.GetUserById;

import java.time.LocalDateTime;
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
    private final GetUserById getUserById;

    @GetMapping
    public List<TaskListDto> getAll() throws ExecutionException, InterruptedException {
        var taskList = getAllTasks.execute(null)
                .get()
                .getOrElseThrow(failure -> switch (failure){
                    case InvalidValue invalidValue -> new BadRequestDto(invalidValue);
                    default -> new InternalServerErrorDto(failure);
                });
        List<TaskListDto> dtoList = new ArrayList<>();
        for(Task task : taskList){
            dtoList.add(new TaskListDto(task));
        }
        return dtoList;
    }
    @PostMapping
    public void create(@RequestBody TaskRequestDto taskRequestDto) throws ExecutionException, InterruptedException {
        var author = getUserById.execute(taskRequestDto.getAuthor()).get().get();
        var executor = getUserById.execute(taskRequestDto.getExecutor()).get().get();
        var task = Task.create(0,
                taskRequestDto.getName(),
                taskRequestDto.getContentType(),
                taskRequestDto.getDescription(),
                null,
                author,
                executor,
                LocalDateTime.now(),
                taskRequestDto.getDateExpired(),
                null,
                null,
                null);
        if(task.isLeft()){
            if(task.getLeft() instanceof InvalidValue){
                throw new BadRequestDto(task.getLeft());
            }
            else {
                throw new InternalServerErrorDto(task.getLeft());
            }
        }
        createTask.execute(task.get());
    }
    @GetMapping(path = "/{id}")
    public CompletableFuture<TaskDto> getTaskById(@PathVariable Integer id) throws ExecutionException, InterruptedException {
        var result = getTaskById.execute(id);
        if(result.get().isLeft()){
            var temp = result.get().getLeft();
            throw new NotFoundDto(temp);
        }
        return CompletableFuture.completedFuture(new TaskDto(result.get().get()));
    }

    @PutMapping(path = "/{id}")
    public void updateTask(@RequestBody TaskRequestDto taskRequestDto, @PathVariable Integer id) throws ExecutionException, InterruptedException {
        var author = getUserById.execute(taskRequestDto.getAuthor()).get().get();
        var executor = getUserById.execute(taskRequestDto.getExecutor()).get().get();
        var updatedTask = Task.create(id,
                taskRequestDto.getName(),
                taskRequestDto.getContentType(),
                taskRequestDto.getDescription(),
                null,
                author,
                executor,
                null,
                taskRequestDto.getDateExpired(),
                null,
                null,
                null).get();
        updateTask.execute(updatedTask);
    }
    @DeleteMapping(path = "/{id}")
    public void deleteTaskById(@PathVariable Integer id){
        deleteTaskById.execute(id);
    }

}
