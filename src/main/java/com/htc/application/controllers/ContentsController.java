package com.htc.application.controllers;

import com.htc.application.dto.content.ContentResponse;
import com.htc.application.services.ContentsService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
@AllArgsConstructor
@RequestMapping("/api/contents")
public class ContentsController {

  ContentsService contentsService;

  /**
   * Получение списка контента.
   *
   * @return Список контента.
   */
  @GetMapping
  @Async
  public CompletableFuture<List<ContentResponse>> getAll() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    return contentsService.getAll(securityContext.getAuthentication().getAuthorities());
  }

  /**
   * Создание контента.
   *
   * @param file   Файл.
   * @param taskId Идентификатор задачи.
   * @return void.
   */
  @PostMapping
  @Async
  public CompletableFuture<Void> create(@RequestParam("file") MultipartFile file,
                                        @RequestParam("task") String taskId) {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    int authorId = (int) securityContext.getAuthentication().getDetails();
    return contentsService.uploadContent(
            authorId,
            securityContext.getAuthentication().getAuthorities(),
            file, taskId);
  }

  /**
   * Удаление контента по идентификатору.
   *
   * @param id Идентификатор контента.
   * @return void.
   */
  @DeleteMapping("/{id}")
  @Async
  public CompletableFuture<Void> delete(@PathVariable("id") String id) {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    return contentsService.delete(
            securityContext.getAuthentication().getAuthorities(),
            id);
  }
}
