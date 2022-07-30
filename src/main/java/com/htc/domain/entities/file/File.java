package com.htc.domain.entities.file;

import com.htc.domain.entities.AbstractApplicationFormat;
import com.htc.domain.entities.ResponseConvertable;
import java.time.LocalDate;

/**
 * Интерфейс, описывающий сущность файла.
 */
public interface File extends ResponseConvertable {

  /**
   * Формат файла.
   */
  enum FileFormat implements AbstractApplicationFormat {

    DOC,

    DOCX,

    XLS,

    XLSX,

    PDF
  }

  /**
   * Идентификатор файла.
   *
   * @return Идентификатор файла.
   */
  Integer getId();

  /**
   * Имя файла.
   *
   * @return Имя файла.
   */
  String getName();

  /**
   * Дата создания файла.
   *
   * @return Дата создания файла.
   */
  LocalDate getDateCreated();

  /**
   * Формат файла.
   *
   * @return Формат файла.
   */
  FileFormat getFormat();

  /**
   * URL файла.
   *
   * @return URL файла.
   */
  String getUrl();
}
