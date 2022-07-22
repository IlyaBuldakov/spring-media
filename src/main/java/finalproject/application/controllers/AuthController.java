package finalproject.application.controllers;

import finalproject.application.dto.auth.AuthLoginRequestDto;
import finalproject.application.dto.auth.AuthLoginResponseDto;
import finalproject.application.dto.auth.AuthRefreshTokenDto;
import finalproject.application.services.auth.AuthService;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер авторизации.
 */
@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<AuthLoginResponseDto> login(@RequestBody AuthLoginRequestDto authRequest)
          throws ExecutionException, InterruptedException {
    final AuthLoginResponseDto token = authService.login(authRequest);
    return ResponseEntity.ok(token);
  }


  @PostMapping("/refresh-token")
  public ResponseEntity<AuthLoginResponseDto> getNewRefreshToken(@RequestBody AuthRefreshTokenDto
                                                                           request)
          throws ExecutionException, InterruptedException {
    final AuthLoginResponseDto token = authService.refresh(request.getRefreshToken());
    return ResponseEntity.ok(token);
  }

}
