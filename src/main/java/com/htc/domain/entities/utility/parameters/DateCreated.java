package com.htc.domain.entities.utility.parameters;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValueParam;
import com.htc.domain.entities.failures.InvalidValues;
import io.vavr.control.Either;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

/**
 *  Класс даты создания.
 */
public class DateCreated extends EntityParameter<String> {
  private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";

  private DateCreated(String value) {
    super(value);
  }

  /**
   * Создание класса даты создания на текущее время.
   *
   * @return result дата создания
   */
  public static Either<Failure, DateCreated> create() {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
    sdf.format(cal.getTime());
    String value = sdf.format(cal.getTime());
    return create(value);
  }

  /**
   * Создание класса даты создания на заданное время.
   *
   * @return result дата создания
   */
  public static Either<Failure, DateCreated> create(String date) {
    return (!(date.length() > 0))
            ? Either.left(new InvalidValues(Map.of(
                    InvalidValueParam.INVALID_ENTITY_DATE_CREATED, "date Created")))
            : Either.right(new DateCreated(date));
  }
}
