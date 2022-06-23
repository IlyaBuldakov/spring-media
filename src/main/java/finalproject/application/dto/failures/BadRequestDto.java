package finalproject.application.dto.failures;

import finalproject.domain.entities.failures.Failure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Ошибка запроса.
 */
public class BadRequestDto extends FailureDto {

  private @Getter FieldInvalidDto[] problems;
  public BadRequestDto (Failure failure, FieldInvalidDto[] problems) {
    super (HttpStatus.BAD_REQUEST, failure);
    this.problems = problems;
  }
  /**
   * Возвращает @return проблемы запроса.
   */


}