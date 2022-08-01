package com.htc.application.controllers;

import com.htc.application.utility.FileParsing;
import com.htc.domain.usecases.file.DeleteFileById;
import com.htc.domain.usecases.file.UploadFile;
import com.htc.utility.Controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping(path = "api/files")
@AllArgsConstructor
@Tags(@Tag(name = "Файлы"))
public class FileController {
  private UploadFile uploadFile;
  private DeleteFileById deleteFileById;

  /**
   * Загружает файл.
   */
  @PostMapping(consumes = { "multipart/form-data" })
  @Operation(summary = "Загрузить новый файл в задачу")
  public void upload(
      @RequestParam("file") MultipartFile file,
      @RequestParam("taskId") int taskId,
      HttpServletRequest request) throws Exception {
    UploadFile.Params params = FileParsing.fileParsing(file, taskId, request);
    assert params != null;
    Controllers.handleRequest(
        uploadFile,
        new UploadFile.Params(
            params.name(), "name",
            params.dateCreated(), "dateCreated",
            params.format(), "format",
            params.urlFile(), "urlFile",
            params.taskId(), "taskId"),
        null);
  }

  /**
   * Удаляет файл.
   */
  @DeleteMapping(path = "/{id}")
  @Operation(summary = "Удалить файл по идентификатору")
  @Async
  public void delete(@PathVariable int id) {
    Controllers.handleRequest(
        deleteFileById,
        new DeleteFileById.Params(id, "id"),
        null);
  }
}
