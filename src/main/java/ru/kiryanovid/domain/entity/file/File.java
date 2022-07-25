package ru.kiryanovid.domain.entity.file;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Файл.
 */
@AllArgsConstructor
@NoArgsConstructor
public class File {
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