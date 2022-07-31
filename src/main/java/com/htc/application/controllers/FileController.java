package com.htc.application.controllers;

import com.htc.application.dtos.file.FileRequest;
import com.htc.application.dtos.file.FileResponse;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.entities.utility.parameters.file.FileBody;
import com.htc.domain.entities.utility.parameters.file.FileName;
import com.htc.domain.entities.utility.parameters.file.FileUrlPath;
import com.htc.domain.usecases.file.DeleteFileById;
import com.htc.domain.usecases.file.GetFileById;
import com.htc.domain.usecases.file.UploadFile;
import com.htc.utility.ControllerHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер файла.
 */
@AllArgsConstructor
@RestController
@SecurityRequirement(name = "JWT")
@Tags(@Tag(name = "Файлы"))
@RequestMapping(path = "api/files")
public class FileController {
  private UploadFile uploadFile;
  private GetFileById getFileById;
  private DeleteFileById deleteFileById;

  /**
   * Добавление файла.
   */
  @Operation(summary = "Добавить нового файла.")
  @PostMapping
  public void upload(@RequestBody FileRequest fileRequest) {
    ControllerHelper.customRequest(
            uploadFile,
            new UploadFile.Params(
                    FileName.create(fileRequest.getName()).get(),
                    fileRequest.getFormat(),
                    FileUrlPath.create(fileRequest.getFileUrlPath()).get(),
                    FileBody.create(fileRequest.getFile()).get()
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
  @Operation(summary = "Получить файл по идентификатору.")
  @GetMapping(path = "/{id}")
  public CompletableFuture<FileResponse> get(@PathVariable Long id) {
    return ControllerHelper.customRequest(
            getFileById,
            new GetFileById.Params(Id.create(id).get()),
            FileResponse::new
    );
  }

  /**
   * Удаление файла.
   *
   * @param id идентификатор
   */
  @Operation(summary = "Удалить файл по идентификатору.")
  @DeleteMapping(path = "/{id}")
  public CompletableFuture<Void> delete(@PathVariable Long id) {
    return ControllerHelper.customRequest(
            deleteFileById,
            new DeleteFileById.Params(Id.create(id).get()),
            null
    );
  }
}
