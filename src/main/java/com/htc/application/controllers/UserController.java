package com.htc.application.controllers;

import com.htc.application.dto.errors.BadRequest;
import com.htc.application.dto.errors.HttpError;
import com.htc.application.dto.user.UserRequest;
import com.htc.application.dto.user.UserResponse;
import com.htc.domain.entities.User;
import com.htc.domain.entities.Id;
import com.htc.domain.service.UserService;
import com.htc.domain.usecases.BaseUseCase;
import com.htc.domain.usecases.UserUseCase;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import java.util.List;
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
 * Контроллер для работы с пользователями.
 */
@RestController
@RequestMapping(path = "api/users")
@SecurityRequirement(name = "JWT")
@Tags(@Tag(name = "Пользователи"))
@AllArgsConstructor
public class UserController {
  UserService userService;

  /**
   * Создать нового пользователя.
   *
   * @param subjectId Идентификатор пользователя, выполняющего данную операцию.
   * @param userRequest Представление сущности пользователя.
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
      @RequestBody @Valid UserRequest userRequest
  ) {
    // TODO: Изменить обработку ошибок для атрибутов.
    final var name = User.Name.create(userRequest.name()).get();
    final var email = User.Email.create(userRequest.email()).get();
    final var password = User.Password.create(userRequest.password()).get();
    final var image = User.Image.create(userRequest.image()).get();

    return this.userService
        .create(subjectId, name, email, password, image, userRequest.role())
        .mapLeft(
            failure -> switch (failure) {
              case BaseUseCase.SubjectNotFound ignored ->
                  HttpError.forbidden("Аутентифицированный пользователь не найден.");
              case BaseUseCase.NoAccess ignored -> HttpError.forbidden("Недостаточно прав для выполнения операции.");
              case UserUseCase.EmailAlreadyExists ignored -> new BadRequest(
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

  /**
   * Изменить пользователя по идентификатору.
   *
   * @param subjectId Идентификатор пользователя, выполняющего данную операцию.
   * @param targetId Идентификатор пользователя.
   * @param userRequest Представление сущности пользователя.
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
      @RequestBody @Valid UserRequest userRequest
  ) {

    final var name = User.Name.create(userRequest.name()).get();
    final var email = User.Email.create(userRequest.email()).get();
    final var password = User.Password.create(userRequest.password()).get();
    final var image = User.Image.create(userRequest.image()).get();

    return this.userService
        .update(subjectId, targetId, name, email, password, image, userRequest.role())
        .mapLeft(
            failure -> switch (failure) {
              case BaseUseCase.SubjectNotFound ignored ->
                  HttpError.forbidden("Аутентифицированный пользователь не найден.");
              case BaseUseCase.NoAccess ignored -> HttpError.forbidden("Недостаточно прав для выполнения операции.");
              case UserUseCase.NotFound ignored -> HttpError.notFound("Запрошенный пользователь не найден.");
              case UserUseCase.EmailAlreadyExists ignored -> new BadRequest(
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
    return this.userService
        .delete(subjectId, targetId)
        .mapLeft(
            failure -> switch (failure) {
              case BaseUseCase.SubjectNotFound ignored ->
                  HttpError.forbidden("Аутентифицированный пользователь не найден.");
              case BaseUseCase.NoAccess ignored -> HttpError.forbidden("Недостаточно прав для выполнения операции.");
              case UserUseCase.NotFound ignored -> HttpError.notFound("Запрошенный пользователь не найден.");
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
    return this.userService
        .get(subjectId, targetId)
        .bimap(
            failure -> switch (failure) {
              case BaseUseCase.SubjectNotFound ignored ->
                  HttpError.forbidden("Аутентифицированный пользователь не найден.");
              case BaseUseCase.NoAccess ignored -> HttpError.forbidden("Недостаточно прав для выполнения операции.");
              case UserUseCase.NotFound ignored -> HttpError.notFound("Запрошенный пользователь не найден.");
              default -> new BadRequest("Запрос содержит некорректные данные.");
            },
            UserResponse::new
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
    return this.userService
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
                .map(UserResponse::new)
                .toList()
        )
        .fold(
            HttpError::toResponseEntity,
            ResponseEntity::ok
        );
  }
}
