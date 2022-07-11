package com.htc.application.controllers;

import com.htc.application.dtos.file.FileRequest;
import com.htc.application.dtos.file.FileResponse;
import com.htc.domain.usecases.file.DeleteFileById;
import com.htc.domain.usecases.file.GetFileById;
import com.htc.domain.usecases.file.UploadFile;
import com.htc.utility.ControllerHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер файла.
 */
@RestController
@RequestMapping(path = "api/files")
@AllArgsConstructor
public class FileController {
  private UploadFile uploadFile;
  private GetFileById getFileById;
  private DeleteFileById deleteFileById;

  /**
   * Добавление файла.
   */
  @PostMapping
  @Operation(summary = "Добавить новый файл.")
  @Async
  public void upload(@RequestBody FileRequest fileRequest) {
    ControllerHelper.customRequest(
            uploadFile,
            new UploadFile.Params(
                    fileRequest.getName(), "name",
                    fileRequest.getFormat(), "format",
                    fileRequest.getFileUrlPath(), "fileUrlPath",
                    fileRequest.getFile(), "fileBody"
            ),
            null
    );
  }

  /**
   * Получение файла.
   *
   * @param id идентификатор
   * @return file файл
   */
  @GetMapping(path = "/{id}")
  @Async
  public CompletableFuture<FileResponse> get(@PathVariable Long id) {
    return ControllerHelper.customRequest(
            getFileById,
            new GetFileById.Params(id, "id"),
            FileResponse::new
    );
  }

  /**
   * Удаление файла.
   *
   * @param id идентификатор
   */
  @DeleteMapping(path = "/{id}")
  @Async
  public CompletableFuture<Void> delete(@PathVariable Long id) {
    return ControllerHelper.customRequest(
            deleteFileById,
            new DeleteFileById.Params(id, "id"),
            null
    );
  }
}
