package com.htc.application.controllers;

import com.htc.application.dto.errors.BadRequest;
import com.htc.application.dto.errors.HttpError;
import com.htc.application.dto.file.FileRequestDto;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.service.FileService;
import com.htc.domain.service.TaskService;
import com.htc.domain.usecases.BaseUseCase;
import com.htc.domain.usecases.FileUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с файлами.
 */
@RestController
@RequestMapping("/api/files")
@AllArgsConstructor
@SecurityRequirement(name = "JWT")
@Tags(@Tag(name = "Файлы"))
public class FileController {

  private final FileService fileService;

  private final TaskService taskService;

  @PostMapping
  @Operation(summary = "Загрузить новый файл в задачу")
  public void upload(
      @AuthenticationPrincipal Id subjectId,
      @RequestBody FileRequestDto fileRequestDto) {
    fileService.create(
        subjectId,
        fileRequestDto.file(),
        fileRequestDto.taskId()
    );
  }

  @DeleteMapping(path = "/{id}")
  @Operation(summary = "Удалить файл по ID")
  public ResponseEntity<Object> delete(
      @AuthenticationPrincipal Id subjectId,
      @PathVariable("id") Id targetId
  ) {
    return this.fileService
        .delete(subjectId, targetId)
        .mapLeft(
            failure -> switch (failure) {
              case BaseUseCase.SubjectNotFound ignored ->
                  HttpError.forbidden("Аутентифицированный пользователь не найден.");
              case BaseUseCase.NoAccess ignored -> HttpError.forbidden("Недостаточно прав для выполнения операции.");
              case FileUseCase.NotFound ignored -> HttpError.notFound("Запрошенный пользователь не найден.");
              default -> new BadRequest("Запрос содержит некорректные данные.");
            }
        )
        .fold(
            HttpError::toResponseEntity,
            unused -> new ResponseEntity<>(HttpStatus.NO_CONTENT)
        );
  }
}