package finalproject.application.controllers;

import finalproject.application.dto.failures.FailureConverter;
import finalproject.application.services.AuthService;
import finalproject.application.services.FileService;
import finalproject.application.services.FileStorageService;
import finalproject.domain.entities.filedocuments.FileDocument;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@RestController
@RequestMapping("api/files")
public class FileController {

  FileStorageService fileStorageService;
  AuthService authService;
  FileService fileService;

  @ApiOperation(value = "", authorizations = { @Authorization(value = "Bearer") })
  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  @PostMapping(consumes = {"multipart/form-data"})
  public CompletableFuture<FileDocument> uploadContent(@RequestPart("file") MultipartFile file,
                                                       @RequestParam("task") int taskId,
                                                       HttpServletRequest request) {

    int autorizedUserId = authService.getId();
    return fileService.uploadFileToTask(file, taskId, autorizedUserId)
            .thenApply(either -> either.getOrElseThrow(
                    failure -> FailureConverter.convert(failure)));


  }

  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  @ApiOperation(value = "", authorizations = { @Authorization(value = "Bearer") })
  @DeleteMapping("/{id}")
  public CompletableFuture<Void> deleteFile(@PathVariable int id) {
    int autorizedUserId = authService.getId();
    return fileService.deleteFileById(id, autorizedUserId)
            .thenApply(either -> either.getOrElseThrow(failure -> FailureConverter.convert(failure)));
  }
}
