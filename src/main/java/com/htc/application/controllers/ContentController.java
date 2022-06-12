package com.htc.application.controllers;

import com.htc.domain.entities.content.Content;
import com.htc.domain.usecases.content.DeleteContentById;
import com.htc.domain.usecases.content.GetContentFeed;
import com.htc.domain.usecases.content.GetContentsByTaskId;
import com.htc.domain.usecases.content.UploadContent;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с контентом.
 */
@RestController
@RequestMapping(path = "api/contents")
@AllArgsConstructor
public class ContentController {
  private UploadContent uploadContent;
  private GetContentsByTaskId getContentsByTaskId;
  private DeleteContentById deleteContentById;
  private GetContentFeed getContentFeed;

  /**
   * Создаёт контент.
   */
  @PostMapping
  public void upload(Content content) {
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
  public Iterable<Content> getContentsByTaskId(@PathVariable int taskId)
      throws ExecutionException, InterruptedException {
    return getContentsByTaskId.execute(taskId)
        .get()
        .get();
  }

  /**
   * Создаёт контент.
   */
  @GetMapping
  public void getContentFeed() {
    throw new UnsupportedOperationException("Метод не реализован");
  }
}
