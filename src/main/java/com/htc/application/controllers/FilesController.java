package com.htc.application.controllers;

import com.htc.application.services.FilesService;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Контроллер для работы с файлами.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/files")
public class FilesController {

  FilesService filesService;

  @PostMapping
  @Async
  public CompletableFuture<Void> uploadFile(@RequestParam("file") MultipartFile file,
                                            @RequestParam("task") String taskId) {
    return filesService.uploadFile(file, taskId);
  }

  @DeleteMapping("/{id}")
  @Async
  public CompletableFuture<Void> deleteFile(@PathVariable("id") String fileId) {
    return filesService.deleteFile(fileId);
  }
}
