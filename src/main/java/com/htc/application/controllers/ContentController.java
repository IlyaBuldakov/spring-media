package com.htc.application.controllers;

import com.htc.application.dto.content.ContentRequest;
import com.htc.domain.entities.Content;
import com.htc.domain.usecases.content.DeleteContentById;
import com.htc.domain.usecases.content.GetContentFeed;
import com.htc.domain.usecases.content.UploadContent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
 * Контроллер для работы с контентом.
 */
@RestController
@RequestMapping(path = "api/contents")
@SecurityRequirement(name = "JWT")
@AllArgsConstructor
public class ContentController {
  private UploadContent uploadContent;
  private DeleteContentById deleteContentById;
  private GetContentFeed getContentFeed;

  /**
   * Загружает контент.
   *
   * @param contentRequest Представление сущности контента.
   */
  @PostMapping
  @Operation(summary = "Создать новый контент.")
  @Async
  public void upload(@RequestBody ContentRequest contentRequest) {
    throw new UnsupportedOperationException("Метод не реализован");
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
