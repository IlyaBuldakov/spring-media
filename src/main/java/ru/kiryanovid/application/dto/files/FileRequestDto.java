package ru.kiryanovid.application.dto.files;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Представление сущности файл (запрос).
 */
@AllArgsConstructor
public class FileRequestDto {
  /**
  * Файл.
  */
  @Getter private FileDto file;

  /**
  * Идентификатор задачи.
  */
  @Getter private Integer id;
}
