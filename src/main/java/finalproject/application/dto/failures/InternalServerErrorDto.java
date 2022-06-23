package finalproject.application.dto.failures;

import finalproject.domain.entities.failures.Failure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Ошибка сервера.
 */

public class InternalServerErrorDto extends FailureDto {

  public InternalServerErrorDto (Failure failure) {
    super (HttpStatus.INTERNAL_SERVER_ERROR, failure);
  }

}