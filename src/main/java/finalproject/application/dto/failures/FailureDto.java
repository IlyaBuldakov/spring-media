package finalproject.application.dto.failures;

import finalproject.domain.entities.failures.Failure;
import lombok.Getter;
import org.springframework.http.HttpStatus;


public class FailureDto extends RuntimeException {

  @Getter
  HttpStatus status;
  @Getter
  int statusCode;
  @Getter
  String message;

  public FailureDto(HttpStatus status, Failure failure) {
    this.status = status;
    this.message = failure.getMessage();
    this.statusCode = status.value();

  }


}
