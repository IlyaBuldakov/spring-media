package ru.kiryanovid.application.dto.content;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.kiryanovid.application.dto.files.FileDto;

/**
 * Представление сущности контента (запрос).
 */
@AllArgsConstructor
public class ContentCreateRequestDto {
  /**
  * Файл.
  */
  @Getter private FileDto file;

  /**
  * Идентификатор задачи.
  */
  @Getter private Integer task;
}
