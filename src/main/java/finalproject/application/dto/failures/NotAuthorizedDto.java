package finalproject.application.dto.failures;

import finalproject.domain.entities.failures.Failure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Ошибка авторизации.
 */

public class NotAuthorizedDto extends FailureDto {

  public NotAuthorizedDto(Failure failure) {
    super(HttpStatus.UNAUTHORIZED, failure);
  }

}