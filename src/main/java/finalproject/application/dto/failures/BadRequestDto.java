package finalproject.application.dto.failures;

import finalproject.domain.entities.failures.Failure;
import java.util.Arrays;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Ошибка запроса.
 */
public class BadRequestDto extends FailureDto {

  private @Getter FieldInvalidDto[] problems;

  /**
   * Возвращает исключение, вызванное некорректным запросом.
   *
   * @param failure принимает ошибку со списком проблемных полей
   */

  public BadRequestDto(Failure failure) {
    super(HttpStatus.BAD_REQUEST, failure);
    this.problems = Arrays.stream(failure.getProblems())
            .map(FieldInvalidDto::new)
            .toArray(FieldInvalidDto[]::new);
  }



}