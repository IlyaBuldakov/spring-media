package com.htc.application.controllers;

import com.htc.application.dto.errors.BadRequest;
import com.htc.application.dto.errors.HttpError;
import com.htc.application.dto.task.TaskRequestDto;
import com.htc.application.dto.task.TaskResponse;
import com.htc.application.dto.user.UserResponse;
import com.htc.domain.entities.Task;
import com.htc.domain.entities.Id;
import com.htc.domain.service.TaskService;
import com.htc.domain.service.UserService;
import com.htc.domain.usecases.BaseUseCase;
import com.htc.domain.usecases.TaskUseCase;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с задачами.
 */
@RestController
@RequestMapping(path = "api/tasks")
@AllArgsConstructor
@SecurityRequirement(name = "JWT")
@Tags(@Tag(name = "Задачи"))
public class TaskController {
  private TaskService taskService;
  private UserService userService;


  /**
   * Создаёт задачу.
   *
   * @param subjectId Идентификатор пользователя, выполняющего данную операцию.
   * @param taskRequestDto Представление сущности задачи.
   * @return Ответ с пустым телом.
   */
  @PostMapping
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "201",
          content = @Content
      ),
      @ApiResponse(
          responseCode = "400",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = BadRequest.class)
          )
      ),
      @ApiResponse(
          responseCode = "403",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = HttpError.class)
          )
      ),
      @ApiResponse(
          responseCode = "404",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = HttpError.class)
          )
      ),
      @ApiResponse(
          responseCode = "500",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = HttpError.class)
          )
      )
  })
  public ResponseEntity<Object> create(
      @AuthenticationPrincipal Id subjectId,
      @RequestBody @Valid TaskRequestDto taskRequestDto
  ) {
    final var name = Task.Name.create(taskRequestDto.name()).get();
    var description = Task.Description.create(taskRequestDto.description()).get();
    final var authorId = Id.create(taskRequestDto.author()).get();
    final var author = userService.get(subjectId, authorId).get();
    final var executorId = Id.create(taskRequestDto.executor()).get();
    final var executor = userService.get(subjectId, executorId).get();


    return this.taskService
        .create(
            subjectId,
            name,
            taskRequestDto.contentType(),
            description,
            author,
            executor,
            taskRequestDto.dateExpired())
        .mapLeft(
            failure -> switch (failure) {
              case BaseUseCase.SubjectNotFound ignored ->
                  HttpError.forbidden("Аутентифицированный пользователь не найден.");
              case BaseUseCase.NoAccess ignored -> HttpError.forbidden("Недостаточно прав для выполнения операции.");
              default -> new BadRequest("Запрос содержит некорректные данные.");
            }
        )
        .fold(
            HttpError::toResponseEntity,
            unused -> new ResponseEntity<>(HttpStatus.CREATED)
        );
  }

  /**
   * Изменить пользователя по идентификатору.
   *
   * @param subjectId Идентификатор пользователя, выполняющего данную операцию.
   * @param targetId Идентификатор задачи.
   * @param taskRequestDto Представление сущности задачи.
   * @return Ответ с пустым телом.
   */
  @PutMapping(path = "/{id}")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "204",
          content = @Content
      ),
      @ApiResponse(
          responseCode = "400",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = BadRequest.class)
          )
      ),
      @ApiResponse(
          responseCode = "403",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = HttpError.class)
          )
      ),
      @ApiResponse(
          responseCode = "404",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = HttpError.class)
          )
      ),
      @ApiResponse(
          responseCode = "500",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = HttpError.class)
          )
      )
  })
  public ResponseEntity<Object> update(
      @AuthenticationPrincipal Id subjectId,
      @PathVariable("id") Id targetId,
      @RequestBody @Valid TaskRequestDto taskRequestDto
  ) {

    final var name = Task.Name.create(taskRequestDto.name()).get();
    var description = Task.Description.create(taskRequestDto.description()).get();
    final var authorId = Id.create(taskRequestDto.author()).get();
    final var author = userService.get(subjectId, authorId).get();
    final var executorId = Id.create(taskRequestDto.executor()).get();
    final var executor = userService.get(subjectId, executorId).get();

    return this.taskService
        .update(
            subjectId,
            targetId,
            name,
            taskRequestDto.contentType(),
            description,
            author,
            executor,
            taskRequestDto.dateExpired())
        .mapLeft(
            failure -> switch (failure) {
              case BaseUseCase.SubjectNotFound ignored ->
                  HttpError.forbidden("Аутентифицированный пользователь не найден.");
              case BaseUseCase.NoAccess ignored -> HttpError.forbidden("Недостаточно прав для выполнения операции.");
              case TaskUseCase.NotFound ignored -> HttpError.notFound("Запрошенный пользователь не найден.");
              default -> new BadRequest("Запрос содержит некорректные данные.");
            }
        )
        .fold(
            HttpError::toResponseEntity,
            unused -> new ResponseEntity<>(HttpStatus.NO_CONTENT)
        );
  }

  /**
   * Удалить пользователя по идентификатору.
   *
   * @param subjectId Идентификатор пользователя, выполняющего данную операцию.
   * @param targetId Идентификатор пользователя.
   * @return Ответ с пустым телом.
   */
  @DeleteMapping(path = "/{id}")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "204",
          content = @Content
      ),
      @ApiResponse(
          responseCode = "400",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = BadRequest.class)
          )
      ),
      @ApiResponse(
          responseCode = "403",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = HttpError.class)
          )
      ),
      @ApiResponse(
          responseCode = "404",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = HttpError.class)
          )
      ),
      @ApiResponse(
          responseCode = "500",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = HttpError.class)
          )
      )
  })
  public ResponseEntity<Object> delete(
      @AuthenticationPrincipal Id subjectId,
      @PathVariable("id") Id targetId
  ) {
    return this.taskService
        .delete(subjectId, targetId)
        .mapLeft(
            failure -> switch (failure) {
              case BaseUseCase.SubjectNotFound ignored ->
                  HttpError.forbidden("Аутентифицированный пользователь не найден.");
              case BaseUseCase.NoAccess ignored -> HttpError.forbidden("Недостаточно прав для выполнения операции.");
              case TaskUseCase.NotFound ignored -> HttpError.notFound("Запрошенный пользователь не найден.");
              default -> new BadRequest("Запрос содержит некорректные данные.");
            }
        )
        .fold(
            HttpError::toResponseEntity,
            unused -> new ResponseEntity<>(HttpStatus.NO_CONTENT)
        );
  }

  /**
   * Получить пользователя по идентификатору.
   *
   * @param subjectId Идентификатор пользователя, выполняющего данную операцию.
   * @param targetId Идентификатор пользователя.
   * @return Представление сущности пользователя.
   */
  @GetMapping(path = "/{id}")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = UserResponse.class)
          )
      ),
      @ApiResponse(
          responseCode = "400",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = BadRequest.class)
          )
      ),
      @ApiResponse(
          responseCode = "403",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = HttpError.class)
          )
      ),
      @ApiResponse(
          responseCode = "404",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = HttpError.class)
          )
      ),
      @ApiResponse(
          responseCode = "500",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = HttpError.class)
          )
      )
  })
  public ResponseEntity<Object> get(
      @AuthenticationPrincipal Id subjectId,
      @PathVariable("id") Id targetId
  ) {
    return this.taskService
        .get(subjectId, targetId)
        .bimap(
            failure -> switch (failure) {
              case BaseUseCase.SubjectNotFound ignored ->
                  HttpError.forbidden("Аутентифицированный пользователь не найден.");
              case BaseUseCase.NoAccess ignored -> HttpError.forbidden("Недостаточно прав для выполнения операции.");
              case TaskUseCase.NotFound ignored -> HttpError.notFound("Запрошенная задача не найдена.");
              default -> new BadRequest("Запрос содержит некорректные данные.");
            },
            TaskResponse::new
        )
        .fold(
            HttpError::toResponseEntity,
            ResponseEntity::ok
        );
  }

  /**
   * Получить список пользователей.
   *
   * @param subjectId Идентификатор пользователя, выполняющего данную операцию.
   * @return Список представлений сущности пользователя.
   */
  @GetMapping
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          content = @Content(
              mediaType = "application/json",
              array = @ArraySchema(schema = @Schema(implementation = UserResponse.class))
          )
      ),
      @ApiResponse(
          responseCode = "400",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = BadRequest.class))
      ),
      @ApiResponse(
          responseCode = "403",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = HttpError.class))
      ),
      @ApiResponse(
          responseCode = "404",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = HttpError.class))
      ),
      @ApiResponse(
          responseCode = "500",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = HttpError.class))
      )
  })
  public ResponseEntity<Object> getAll(@AuthenticationPrincipal Id subjectId) {
    return this.taskService
        .getAll(subjectId)
        .bimap(
            failure -> switch (failure) {
              case BaseUseCase.SubjectNotFound ignored ->
                  HttpError.forbidden("Аутентифицированный пользователь не найден.");
              case BaseUseCase.NoAccess ignored -> HttpError.forbidden("Недостаточно прав для выполнения операции.");
              default -> new BadRequest("Запрос содержит некорректные данные.");
            },
            users -> users
                .stream()
                .map(TaskResponse::new)
                .toList()
        )
        .fold(
            HttpError::toResponseEntity,
            ResponseEntity::ok
        );
  }
}
