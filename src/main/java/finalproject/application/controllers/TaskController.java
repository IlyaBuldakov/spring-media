package finalproject.application.controllers;

import finalproject.application.dto.failures.BadRequestDto;
import finalproject.application.dto.failures.InternalServerErrorDto;
import finalproject.application.dto.failures.NotFoundDto;
import finalproject.application.dto.task.TaskDto;
import finalproject.application.dto.task.TaskRequestDto;
import finalproject.application.dto.user.UserDto;
import finalproject.application.services.TaskService;
import finalproject.application.services.UserService;
import finalproject.domain.entities.content.ContentType;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.task.Task;
import finalproject.domain.entities.user.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@AllArgsConstructor
@RestController
@RequestMapping("api/tasks")
public class TaskController {

  UserService userService;
  TaskService taskService;

  @ApiOperation(value = "", authorizations = { @Authorization(value="Bearer") })
  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  @PutMapping
  public CompletableFuture<Task> createTask(@RequestBody TaskRequestDto taskdto) throws ExecutionException, InterruptedException {
    User author = userService.getUserById(taskdto.getAuthorId())
            .thenApply(either -> either.getOrElseThrow(failure -> new NotFoundDto(failure)))
            .get();
    User contentMaker = userService.getUserById(taskdto.getContentMakerId())
            .thenApply(either -> either.getOrElseThrow(failure -> new NotFoundDto(failure)))
            .get();
    Task task = Task.create(taskdto.getName(), ContentType.getContentTypeByName(taskdto.getType()),
            taskdto.getDescription(), author, contentMaker, taskdto.getDateExpired()).getOrElseThrow(failure -> new BadRequestDto(failure));
    task.setDateCreated(LocalDateTime.now());
    return taskService.createNewTask(task).thenApply(either -> either.get());


  }

}
