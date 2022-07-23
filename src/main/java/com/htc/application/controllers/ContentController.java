package com.htc.application.controllers;

import com.htc.domain.entities.Content;
import com.htc.domain.usecases.content.DeleteContentById;
import com.htc.domain.usecases.content.GetContentFeed;
import com.htc.domain.usecases.content.UploadContent;
import com.htc.utility.ContentParsing;
import com.htc.utility.Controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Контроллер для работы с контентом.
 */
@RestController
@RequestMapping(path = "api/contents")
@SecurityRequirement(name = "JWT")
@AllArgsConstructor
@Tags(@Tag(name = "Медиаконтент"))
public class ContentController {
  private UploadContent uploadContent;
  private DeleteContentById deleteContentById;
  private GetContentFeed getContentFeed;

  /**
   * Загружает контент.
   */
  @PostMapping(consumes = { "multipart/form-data" })
  @Operation(summary = "Загрузить новый контент в задачу")
  public void upload(
      @RequestParam("file") MultipartFile file,
      @RequestParam("taskId") int taskId,
      @RequestParam("authorId") int authorId,
      HttpServletRequest request) throws Exception {
    UploadContent.Params params = ContentParsing.fileParsing(file, taskId, authorId, request);
    Controllers.handleRequest(
        uploadContent,
        new UploadContent.Params(
            params.type(), "type",
            params.name(), "name",
            params.dateCreated(), "dateCreated",
            params.authorId(), "authorId",
            params.format(), "format",
            params.urlContent(), "urlContent",
            params.urlPreview(), "urlPreview",
            params.taskId(), "taskId"),
        null);
  }

  /**
   * Удаляет контент.
   */
  @DeleteMapping
  public void delete(int id) {
    throw new UnsupportedOperationException("Метод не реализован");
  }

  /**
   * Возвращает контент по идентификатору задачи.
   *
   * @param taskId Идентификатор задачи.
   * @return Комментарии по идентификатору задачи.
   */
  @GetMapping(path = "/{taskId}")
  public Iterable<Content> getContentsByTaskId(@PathVariable int taskId) {
    throw new UnsupportedOperationException("Метод не реализован");
  }

  /**
   * Возвращает содержимое ленты контента.
   */
  @GetMapping
  public void getContentFeed() {
    throw new UnsupportedOperationException("Метод не реализован");
  }
}
