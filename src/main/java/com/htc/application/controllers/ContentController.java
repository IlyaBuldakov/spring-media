package com.htc.application.controllers;

import com.htc.application.dto.content.ContentCreateRequestDto;
import com.htc.application.dto.content.ContentsResponseDto;
import com.htc.application.dto.errors.BadRequest;
import com.htc.application.dto.errors.HttpError;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.service.ContentService;
import com.htc.domain.usecases.BaseUseCase;
import com.htc.domain.usecases.ContentUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с медиаконтентом.
 */
@RestController
@RequestMapping(path = "api/contents")
@AllArgsConstructor
@SecurityRequirement(name = "JWT")
@Tags(@Tag(name = "Медиаконтент"))
public class ContentController {
  private ContentUseCase.Create createContent;

  private final ContentService contentService;

  /**
   * Загрузить новый медиаконтент в задачу.
   */
  @PostMapping
  @Async
  @Operation(summary = "Загрузить новый контент в задачу")
  public void upload(
      @AuthenticationPrincipal Id subjectId,
      @RequestBody ContentCreateRequestDto contentCreateRequestDto) {
    contentService.create(
        subjectId,
        contentCreateRequestDto.file(),
        contentCreateRequestDto.taskId()
    );
  }

  //TODO: Вытащить функцию со свичом в метод другого класса.

  /**
   * Получить содержимое ленты медиаконтента.
   *
   * @param page Номер страницы.
   * @param count Количество контента на страницу.
   * @param author Имя автора контента (запрос).
   * @param date Дата публикации контента (запрос).
   * @param typeId Идентификатор типа контента (запрос).
   * @return Список медиа контента, удовлетворяющий запросу.
   */
  @GetMapping
  @Operation(summary = "Получить содержимое ленты контента")
  public ResponseEntity<Object> getContentList(
      @AuthenticationPrincipal Id subjectId,
      Integer page,
      Integer count,
      String author,
      String date,
      Integer typeId) {

    return this.contentService
        .getFeed(subjectId, page, count, author, date, typeId)
        .bimap(
            failure -> switch (failure) {
              case BaseUseCase.SubjectNotFound ignored ->
                  HttpError.forbidden("Аутентифицированный пользователь не найден.");
              case BaseUseCase.NoAccess ignored -> HttpError.forbidden("Недостаточно прав для выполнения операции.");
              default -> new BadRequest("Запрос содержит некорректные данные.");
            },
            ContentsResponseDto::new
        )
        .fold(
            HttpError::toResponseEntity,
            ResponseEntity::ok
        );
  }


  /**
   * Удаляет медиаконтент.
   *
   * @param subjectId Идентификатор пользователя, выполняющего данную операцию.
   * @param targetId Идентификатор медиаконтента.
   */
  @DeleteMapping(path = "/{id}")
  @Operation(summary = "Удалить контент по идентификатору")
  public ResponseEntity<Object> delete(
      @AuthenticationPrincipal Id subjectId,
      @PathVariable("id") Id targetId
  ) {
    return this.contentService
        .delete(subjectId, targetId)
        .mapLeft(
            failure -> switch (failure) {
              case BaseUseCase.SubjectNotFound ignored ->
                  HttpError.forbidden("Аутентифицированный пользователь не найден.");
              case BaseUseCase.NoAccess ignored -> HttpError.forbidden("Недостаточно прав для выполнения операции.");
              case ContentUseCase.NotFound ignored -> HttpError.notFound("Запрошенный пользователь не найден.");
              default -> new BadRequest("Запрос содержит некорректные данные.");
            }
        )
        .fold(
            HttpError::toResponseEntity,
            unused -> new ResponseEntity<>(HttpStatus.NO_CONTENT)
        );
  }
}