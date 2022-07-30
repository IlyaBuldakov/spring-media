package com.htc.application.controllers;

import com.htc.application.dto.comment.CommentRequestDto;
import com.htc.application.dto.errors.BadRequest;
import com.htc.application.dto.errors.HttpError;
import com.htc.domain.entities.Comment;
import com.htc.domain.entities.Id;
import com.htc.domain.service.CommentService;
import com.htc.domain.service.TaskService;
import com.htc.domain.service.UserService;
import com.htc.domain.usecases.BaseUseCase;
import com.htc.domain.usecases.UserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с комментариями.
 */
@RestController
@RequestMapping(path = "api/comments")
@AllArgsConstructor
@SecurityRequirement(name = "JWT")
@Tags(@Tag(name = "Комментарии"))
public class CommentController {
  private CommentService commentService;
  private UserService userService;
  private TaskService taskService;

  /**
   * Создаёт комментарий.
   */
  @PostMapping
  @Operation(summary = "Добавить новый комментарий в задачу")
  public ResponseEntity<Object> create(
      Id subjectId,
      @RequestBody CommentRequestDto commentRequestDto) {
    final var message = Comment.Message.create(commentRequestDto.message()).get();
    // TODO: из соответствующих методом классов UserController и TaskController выделить
    //  получение пользователя(задачи) с обработкой ошибок и применить его тут
    final var userId = Id.create(commentRequestDto.user()).get();
    final var taskId = Id.create(commentRequestDto.task()).get();
    final var user = userService.get(subjectId, userId).get();
    final var task = taskService.get(subjectId, taskId).get();


    return this.commentService
        .create(subjectId, user, task, message)
        .mapLeft(
            failure -> switch (failure) {
              case BaseUseCase.SubjectNotFound ignored ->
                  HttpError.forbidden("Аутентифицированный пользователь не найден.");
              case BaseUseCase.NoAccess ignored ->
                  HttpError.forbidden("Недостаточно прав для выполнения операции.");
              case UserUseCase.EmailAlreadyExists ignored ->
                  new BadRequest(
                      "Пользователь с указанной электронной почтой уже существует.",
                      () -> List.of(
                          new BadRequest.FieldInvalid(
                              "Пользователь с указанной электронной почтой уже существует.",
                              "email"
                          )
                      )
                  );
              default -> new BadRequest("Запрос содержит некорректные данные.");
            }
        )
        .fold(
            HttpError::toResponseEntity,
            unused -> new ResponseEntity<>(HttpStatus.CREATED)
        );
  }
}
