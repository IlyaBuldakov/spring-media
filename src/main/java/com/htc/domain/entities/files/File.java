package com.htc.domain.entities.files;

import java.time.LocalDateTime;
import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Файл. Используется как прилежение к задаче.
 */
@AllArgsConstructor
public class File {

  /**
   * Индентификатор файла.
   *
   * @return Индентификатор файла.
   */
  private @Getter int id;
  /**
   * Имя файла.
   *
   * @return Имя файла.
   */
  private @Getter String name;
  /**
   * Дата загрузки файла.
   *
   * @return Дата загрузки файла.
   */
  private @Getter LocalDateTime dateCreated;
  /**
   * Формат файла.
   *
   * @return Формат файла.
   */
  private @Getter FileFormat format;
  /**
   * URL файла.
   *
   * @return URL файла.
   */
  private @Getter String url;

  /**
   * Создаёт тестовый файл.
   *
   * @return Файл.
   */
  public static File createTestFile(int id) {
    return new File(
            id,
            null,
            null,
            null,
            null
    );
  }

  public static File createTestFile() {
    var id = new Random().nextInt(Integer.MAX_VALUE);
    return createTestFile(id);
  }
}
