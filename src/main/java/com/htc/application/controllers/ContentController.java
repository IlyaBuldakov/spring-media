package com.htc.application.controllers;

import com.htc.application.dtos.content.ContentRequest;
import com.htc.application.dtos.content.ContentResponse;
import com.htc.domain.usecases.content.AddContent;
import com.htc.domain.usecases.content.ChangeContentById;
import com.htc.domain.usecases.content.DeleteContentById;
import com.htc.domain.usecases.content.GetAllContent;
import com.htc.domain.usecases.content.GetContentById;
import com.htc.utility.ControllerHelper;
import io.swagger.v3.oas.annotations.Operation;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер контента.
 */
@RestController
@RequestMapping(path = "api/contents")
@AllArgsConstructor
public class ContentController {
  private AddContent addContent;
  private GetContentById getContentById;
  private GetAllContent getAllContent;
  private ChangeContentById changeContentById;
  private DeleteContentById deleteContentById;

  /**
   * Добавление контента.
   */
  @PostMapping
  @Operation(summary = "Добавить новый контент.")
  @Async
  public void add(@RequestBody ContentRequest contentRequest) {
    //TODO Controllers проверить на назначение DTO согласно спецификации
    ControllerHelper.customRequest(
            addContent,
            new AddContent.Params(
                    contentRequest.getTaskId(), "taskId",
                    contentRequest.getFileId(), "fileId"
            ),
            null
    );
  }

  /**
   * Получение контента.
   *
   * @param id идентификатор
   *
   * @return comment контент
   */
  @GetMapping(path = "/{id}")
  @Async
  public CompletableFuture<ContentResponse> get(@PathVariable Long id) {
    return ControllerHelper.customRequest(
            getContentById,
            new GetContentById.Params(id, "id"),
            ContentResponse::new
    );
  }

  /**
   * Получение всего контента.
   *
   * @return list лента контента
   */
  @GetMapping
  @Async
  public CompletableFuture<Iterable<ContentResponse>> getAll() {
    return ControllerHelper.customRequest(
            getAllContent,
            null,
            users -> users.stream()
                    .map(ContentResponse::new)
                    .collect(Collectors.toList())
    );
  }

  /**
   * Обновление контента.
   *
   * @param id идентификатор
   */
  @PutMapping(path = "/{id}")
  @Async
  public void update(@PathVariable Long id, @RequestBody ContentRequest contentRequest) {
    ControllerHelper.customRequest(
            changeContentById,
            new ChangeContentById.Params(
                    id, "id",
                    contentRequest.getTaskId(), "taskId",
                    contentRequest.getFileId(), "fileId"
            ),
            ContentResponse::new
    );
  }

  /**
   * Удаление контента.
   *
   * @param id идентификатор
   */
  @DeleteMapping(path = "/{id}")
  @Async
  public CompletableFuture<Void> delete(@PathVariable Long id) {
    return ControllerHelper.customRequest(
            deleteContentById,
            new DeleteContentById.Params(id, "id"),
            null
    );
  }
}
