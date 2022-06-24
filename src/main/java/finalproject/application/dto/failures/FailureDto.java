package finalproject.application.dto.failures;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import finalproject.domain.entities.failures.Failure;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@JsonIgnoreProperties(value = {"cause", "stackTrace", "suppressed", "localizedMessage" })
public class FailureDto extends RuntimeException {

  @Getter
  @JsonIgnore
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
