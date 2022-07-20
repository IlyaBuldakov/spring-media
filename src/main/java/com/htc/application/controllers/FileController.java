package com.htc.application.controllers;

import com.htc.domain.entities.File;
import com.htc.domain.usecases.file.DeleteFileById;
import com.htc.domain.usecases.file.GetFilesByTaskId;
import com.htc.domain.usecases.file.UploadFile;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с файлами.
 */
@RestController
@RequestMapping(path = "api/files")
@AllArgsConstructor
public class FileController {
  private UploadFile uploadFile;
  private GetFilesByTaskId getFilesByTaskId;
  private DeleteFileById deleteFileById;

  /**
   * Загружает файл.
   */
  @PostMapping
  public void upload(File file) {
    throw new UnsupportedOperationException("Метод не реализован");
  }

  /**
   * Удаляет файл.
   */
  @DeleteMapping(path = "/{id}")
  public void delete(int id) {
    throw new UnsupportedOperationException("Метод не реализован");
  }
}
