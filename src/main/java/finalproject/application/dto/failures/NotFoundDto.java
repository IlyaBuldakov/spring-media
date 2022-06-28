package finalproject.application.dto.failures;

import finalproject.domain.entities.failures.Failure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Ошибка поиска.
 */

public class NotFoundDto extends FailureDto {


  public NotFoundDto(Failure failure) {
    super (HttpStatus.NOT_FOUND, failure);

  }

}