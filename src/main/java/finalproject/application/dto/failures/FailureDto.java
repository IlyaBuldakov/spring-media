package finalproject.application.dto.failures;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import finalproject.domain.entities.failures.Failure;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Ошбий класс DTO исключений, возвращающихся при перехвате Runtime Exception.
 */
@JsonIgnoreProperties(value = {"cause", "stackTrace", "suppressed", "localizedMessage" })
public class FailureDto extends RuntimeException {

  @Getter
  @JsonIgnore
  HttpStatus status;
  @Getter
  int statusCode;
  @Getter
  String message;

  /**
   * Конструктор DTO.
   *
   * @param status - HTTP статус ошибки
   * @param failure - Ошибки, из-за которых выбрасывается исключение.
   */
  public FailureDto(HttpStatus status, Failure failure) {
    this.status = status;
    this.message = failure.getMessage();
    this.statusCode = status.value();

  }


}
