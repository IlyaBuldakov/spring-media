package com.htc.application.dto.file;

import com.htc.application.dto.BaseResponse;
import com.htc.domain.entities.file.File;
import com.htc.domain.entities.file.File.FileFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * Представление файла (ответ).
 */
public class FileResponse implements BaseResponse {

  /**
   * Конструктор из соответствующей сущности.
   *
   * @param file Сущность {@link File файла}.
   */
  public FileResponse(File file) {
    this.id = file.getId();
    this.name = file.getName();
    this.dateCreated = file.getDateCreated();
    this.fileFormat = file.getFormat();
    this.url = file.getUrl();
  }

  /**
   * Идентификатор файла.
   */
  private final @Getter int id;

  /**
   * Имя файла.
   */
  private final @Getter String name;

  /**
   * Дата создания файла.
   */
  private final @Getter LocalDate dateCreated;

  /**
   * Формат файла.
   */
  private final @Getter FileFormat fileFormat;

  /**
   * URL файла на сервере.
   */
  private final @Getter String url;
}
