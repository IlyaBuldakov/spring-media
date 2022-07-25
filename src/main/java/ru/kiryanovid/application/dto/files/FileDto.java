package ru.kiryanovid.application.dto.files;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.kiryanovid.domain.entity.file.Format;

/**
 * Представление сущности файл.
 */
@AllArgsConstructor
public class FileDto {
  /**
  * Идентификатор.
  */
  @Getter private Integer id;

  /**
  * Имя.
  */
  @Getter private String name;

  /**
  * Дата создания.
  */
  @Getter private LocalDateTime dateCreate;

  /**
  * Формат.
  */
  @Getter private Format format;

  /**
  * Ссылка.
  */
  @Getter private String url;
}
