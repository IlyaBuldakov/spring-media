package finalproject.application.dto.failures;

import finalproject.domain.entities.failures.Failure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

/**
 * Ошибка запроса.
 */
public class BadRequestDto extends FailureDto {

  private @Getter FieldInvalidDto[] problems;
  public BadRequestDto (Failure failure) {
    super (HttpStatus.BAD_REQUEST, failure);
    this.problems = Arrays.stream(failure.getProblems())
            .map(FieldInvalidDto::new)
            .toArray(FieldInvalidDto[]::new);
  }
  /**
   * Возвращает @return проблемы запроса.
   */


}