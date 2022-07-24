package com.htc.application.controllers;

import com.htc.service.FileUploadService;
import com.htc.utility.Controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
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
@RequestMapping("/api/files")
@AllArgsConstructor
@SecurityRequirement(name = "JWT")
@Tags(@Tag(name = "Файлы"))
public class FileController {

  private FileUploadService fileUploadService;

  @PostMapping
  @Operation(summary = "Загрузить новый файл в задачу")
  public void uploadFile(@RequestParam("file") MultipartFile file, int taskId) {

    fileUploadService.uploadFile(file);
  }

  @DeleteMapping(path = "/{id}")
  @Async
  @Operation(summary = "Удалить файл по ID")
  public void delete(@PathVariable int id) {

  }
}