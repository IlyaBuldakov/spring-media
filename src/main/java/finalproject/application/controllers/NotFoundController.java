package finalproject.application.controllers;

import finalproject.application.dto.failures.FailureDto;
import finalproject.application.dto.failures.NotFoundDto;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.failures.Messages;
import finalproject.domain.entities.failures.NotFound;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер для страницы 404.
 */
@Controller
public class NotFoundController implements ErrorController {
  @GetMapping("/error")
  public ResponseEntity<FailureDto> notFoundError() {
    FailureDto fail = new NotFoundDto(new NotFound(Messages.NOT_FOUND));
    return new ResponseEntity<>(fail, fail.getStatus());
  }
}
