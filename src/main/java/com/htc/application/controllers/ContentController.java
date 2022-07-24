package com.htc.application.controllers;

import com.htc.application.dto.content.ContentCreateRequestDto;
import com.htc.application.dto.content.ContentsResponseDto;
import com.htc.application.dto.responsestatus.BadRequestResponse;
import com.htc.application.dto.responsestatus.InternalServerErrorResponse;
import com.htc.application.dto.responsestatus.NotFoundResponse;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.usecases.content.CreateContent;
import com.htc.domain.usecases.content.DeleteContentById;
import com.htc.domain.usecases.content.GetContentByQuery;
import com.htc.utility.Controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с медиаконтеном.
 */
@RestController
@RequestMapping(path = "api/contents")
@AllArgsConstructor
@SecurityRequirement(name = "JWT")
@Tags(@Tag(name = "Медиаконтент"))
public class ContentController {
  private DeleteContentById deleteContentById;
  private GetContentByQuery getContentByQuery;
  private CreateContent createContent;

  /**
   * Загрузить новый медиаконтент в задачу.
   */
  @PostMapping
  @Async
  @Operation(summary = "Загрузить новый контент в задачу")
  public void upload(@RequestBody ContentCreateRequestDto contentCreateRequestDto) {
    createContent.execute(
            new CreateContent.Params(
                    contentCreateRequestDto.getFile(),
                    contentCreateRequestDto.getTaskId())
    );
  }

  //TODO: Вытащить функцию со свичом в метод класса Controllers.

  /**
   * Получить содержимое ленты медиаконтента.
   *
   * @param page Номер страницы.
   * @param count Количество контента на страницу.
   * @param author Имя автора контента (запрос).
   * @param date Дата публикации контента (запрос).
   * @param typeId Идентификатор типа контента (запрос).
   * @return Список медиа контента, удовлетварющий запрсам.
   */
  @GetMapping
  @Async
  @Operation(summary = "Получить содержимое ленты контента")
  public CompletableFuture<ContentsResponseDto> getContentList(
          Integer page,
          Integer count,
          String author,
          LocalDate date,
          Integer typeId) {
    var contents = getContentByQuery.execute(new GetContentByQuery.Params(
            page,
            count,
            author,
            date,
            typeId)).thenApply(either -> (either
            .getOrElseThrow(failure -> switch (failure) {
              case InvalidValues invalidValues -> new BadRequestResponse(invalidValues);
              case NotFound ignored -> new NotFoundResponse(failure);
              default -> new InternalServerErrorResponse(failure);
            }))).thenApply(ContentsResponseDto::new);
    return contents;
  }


  /**
   * Удаляет медиаконтент.
   *
   * @param id Идентификатор медиаконтента.
   */
  @DeleteMapping(path = "/{id}")
  @Async
  @Operation(summary = "Удалить контент по идентификатору")
  public void delete(@PathVariable int id) {
    Controllers.handleRequest(
            deleteContentById,
            id,
            null);
  }
}