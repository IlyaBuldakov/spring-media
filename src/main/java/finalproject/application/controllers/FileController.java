package finalproject.application.controllers;

import finalproject.application.dto.failures.FailureConverter;
import finalproject.application.services.AuthService;
import finalproject.application.services.FileService;
import finalproject.application.services.FileStorageService;
import finalproject.domain.entities.filedocuments.FileDocument;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import java.util.concurrent.CompletableFuture;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Контроллер документов.
 */
@AllArgsConstructor
@RestController
@RequestMapping("api/files")
public class FileController {

  FileStorageService fileStorageService;
  AuthService authService;
  FileService fileService;

  /**
   * Загрузка файла в задачу.
   *
   * @param file file
   * @param taskId id задачи
   * @param request запрос
   * @return класс сущности
   */
  @ApiOperation(value = "", authorizations = { @Authorization(value = "Bearer") })
  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  @PostMapping(consumes = {"multipart/form-data"})
  public CompletableFuture<FileDocument> uploadFile(@RequestPart("file") MultipartFile file,
                                                    @RequestParam("task") int taskId,
                                                    HttpServletRequest request) {

    int autorizedUserId = authService.getId();
    return fileService.uploadFileToTask(file, taskId, autorizedUserId)
            .thenApply(either -> either.getOrElseThrow(
                    FailureConverter::convert));


  }

  /**
   * Удаление файла.
   *
   * @param id id файла
   * @return пустой ответ
   */
  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  @ApiOperation(value = "", authorizations = { @Authorization(value = "Bearer") })
  @DeleteMapping("/{id}")
  public CompletableFuture<Void> deleteFile(@PathVariable int id) {
    int autorizedUserId = authService.getId();
    return fileService.deleteFileById(id, autorizedUserId)
            .thenApply(either -> either.getOrElseThrow(FailureConverter::convert));
  }
}
