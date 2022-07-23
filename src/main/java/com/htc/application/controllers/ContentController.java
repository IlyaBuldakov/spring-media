package com.htc.application.controllers;

import com.htc.application.dto.content.ContentsResponseDto;
import com.htc.application.dto.responsestatus.BadRequestResponse;
import com.htc.application.dto.responsestatus.InternalServerErrorResponse;
import com.htc.application.dto.responsestatus.NotFoundResponse;
import com.htc.application.dto.responsestatus.UnauthorizedResponse;
import com.htc.application.service.FileUploadService;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.entities.failures.Unauthorized;
import com.htc.domain.usecases.content.DeleteContentById;
import com.htc.domain.usecases.content.GetContentByQuery;
import com.htc.utility.Controllers;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Контроллер для работы с медиаконтеном.
 */
@RestController
@RequestMapping(path = "api/contents")
@AllArgsConstructor
public class ContentController {
  private DeleteContentById deleteContentById;
  private GetContentByQuery getContentByQuery;
  private FileUploadService fileUploadService;

  /**
   * Загрузить новый медиаконтент в задачу.
   *
   * @param file Загружаемый файл
   * @param taskId Индентификатор родительской задачи.
   */
  @PostMapping
  @Async
  public void upload(@RequestParam("file") MultipartFile file, int taskId) {

    fileUploadService.uploadFile(file);
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
              case Unauthorized ignored -> new UnauthorizedResponse(failure);
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
  public void delete(@PathVariable int id) {
    Controllers.handleRequest(
            deleteContentById,
            id,
            null);
  }
}