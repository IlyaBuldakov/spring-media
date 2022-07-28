package finalproject.application.controllers;


import finalproject.application.dto.content.ContentDto;
import finalproject.application.dto.content.ContentsResponseDto;
import finalproject.application.dto.failures.BadRequestDto;
import finalproject.application.dto.failures.NotFoundDto;
import finalproject.application.services.ContentService;
import finalproject.application.services.FileStorageService;
import finalproject.application.services.TaskService;
import finalproject.domain.entities.content.Content;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Контроллер для загрузки контента.
 */
@AllArgsConstructor
@RestController
public class ContentController {

  FileStorageService fileStorageService;
  TaskService taskService;
  ContentService contentService;


  /**
   * Сохранения файла в задачу.
   *
   * @param file файл
   * @param taskId id задачи
   * @param request запрос
   * @return Строку URL файла
   * @throws IOException исключение при сохранении файла
   */
  @ApiOperation(value = "", authorizations = { @Authorization(value = "Bearer") })
  @PostMapping(value = "/api/contents", consumes = {"multipart/form-data"})
  public CompletableFuture<Content> uploadContent(@RequestPart("file") MultipartFile file,
                                                  @RequestParam("task") int taskId,
                                                  HttpServletRequest request)
          throws IOException {
    return contentService.attachFileToTask(file, taskId)
            .thenApply(either -> either.getOrElseThrow(failure -> {
              if (failure.getProblems() != null) {
                return new BadRequestDto(failure);
              }
              return new NotFoundDto(failure);
            }));

  }

  @ApiOperation(value = "", authorizations = { @Authorization(value = "Bearer") })
  @DeleteMapping("/api/contents/{id}")
  public CompletableFuture<Void> deleteContent(@PathVariable int id) {
    return contentService.deleteContentById(id).thenApply(either -> either.getOrElseThrow(failure -> {
      if (failure.getProblems() != null) {
        return new BadRequestDto(failure);
      }
      return new NotFoundDto(failure);
    }));
  }

  @ApiOperation(value = "", authorizations = { @Authorization(value = "Bearer") })
  @GetMapping("/api/contents")
  public CompletableFuture<ContentsResponseDto> getPublishedContent() {

    return contentService.getAllContent()
            .thenApply(either -> either.get().stream().map(ContentDto::new)
                    .toList()
                    .toArray(new ContentDto[0]))
            .thenApply( list -> new ContentsResponseDto(list, list.length));

  }



}