package finalproject.application.controllers;

import finalproject.application.dto.failures.FailureDto;
import finalproject.application.dto.failures.InternalServerErrorDto;
import finalproject.domain.entities.failures.Failure;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnotherErrorController implements ErrorController {
  @GetMapping("/error")
  public ResponseEntity<FailureDto> anotherError () {
    FailureDto fail = new InternalServerErrorDto(new Failure("Ошибка сервера"));
    return new ResponseEntity<>(fail, fail.getStatus());
  }
}
