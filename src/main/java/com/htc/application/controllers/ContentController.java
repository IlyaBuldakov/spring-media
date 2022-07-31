package com.htc.application.controllers;

import com.htc.application.dtos.content.ContentRequest;
import com.htc.application.dtos.content.ContentResponse;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.usecases.content.AddContent;
import com.htc.domain.usecases.content.ChangeContentById;
import com.htc.domain.usecases.content.DeleteContentById;
import com.htc.domain.usecases.content.GetAllContent;
import com.htc.domain.usecases.content.GetContentById;
import com.htc.utility.ControllerHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@RestController
@SecurityRequirement(name = "JWT")
@Tags(@Tag(name = "Медиаконтент"))
@RequestMapping(path = "api/contents")
public class ContentController {
  private AddContent addContent;
  private GetContentById getContentById;
  private GetAllContent getAllContent;
  private ChangeContentById changeContentById;
  private DeleteContentById deleteContentById;

  /**
   * Добавление контента.
   */
  @Operation(summary = "Добавить новый контент.")
  @PostMapping
  public void add(@RequestBody ContentRequest contentRequest) {
    //TODO Controllers проверить на назначение DTO согласно спецификации
    ControllerHelper.customRequest(
            addContent,
            new AddContent.Params(
                    Id.create(contentRequest.getTaskId()).get(),
                    Id.create(contentRequest.getFileId()).get()
            ),
            null
    );
  }

  /**
   * Получение контента.
   *
   * @param id идентификатор
   * @return comment контент
   */
  @Operation(summary = "Получить контент по идентификатору.")
  @GetMapping(path = "/{id}")
  public CompletableFuture<ContentResponse> get(@PathVariable Long id) {
    return ControllerHelper.customRequest(
            getContentById,
            new GetContentById.Params(Id.create(id).get()),
            ContentResponse::new
    );
  }

  /**
   * Получение всего контента.
   *
   * @return list лента контента
   */
  @Operation(summary = "Получить весь контент.")
  @GetMapping
  public CompletableFuture<List<ContentResponse>> getAll() {
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
  @Operation(summary = "Обновить контент по идентификатору.")
  @PutMapping(path = "/{id}")
  public void update(@PathVariable Long id, @RequestBody ContentRequest contentRequest) {
    ControllerHelper.customRequest(
            changeContentById,
            new ChangeContentById.Params(
                    Id.create(id).get(),
                    Id.create(contentRequest.getTaskId()).get(),
                    Id.create(contentRequest.getFileId()).get()
            ),
            ContentResponse::new
    );
  }

  /**
   * Удаление контента.
   *
   * @param id идентификатор
   */
  @Operation(summary = "Удалить контент по идентификатору.")
  @DeleteMapping(path = "/{id}")
  public CompletableFuture<Void> delete(@PathVariable Long id) {
    return ControllerHelper.customRequest(
            deleteContentById,
            new DeleteContentById.Params(Id.create(id).get()),
            null
    );
  }
}
