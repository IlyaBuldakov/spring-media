package finalproject.application.services.auth;

import finalproject.application.dto.auth.AuthLoginRequestDto;
import finalproject.application.dto.auth.AuthLoginResponseDto;
import finalproject.application.dto.failures.NotAuthorizedDto;
import finalproject.application.services.UserService;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.user.User;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public final class AuthService {
  private final UserService userService;
  private final Map<String, String> refreshStorage = new HashMap<>();
  private final JwtProvider jwtProvider;

  public AuthLoginResponseDto login(@NonNull AuthLoginRequestDto authRequest) throws ExecutionException, InterruptedException {
    final User user = userService.getUserByEmail(authRequest.getEmail()).get().getOrElseThrow(failure -> new NotAuthorizedDto(failure));

    if (user.getPassword().equals(authRequest.getPassword())) {
      final String accessToken = jwtProvider.generateAccessToken(user);
      final String refreshToken = jwtProvider.generateRefreshToken(user);
      refreshStorage.put(user.getEmail(), refreshToken);
      return new AuthLoginResponseDto(accessToken, refreshToken);
    } else {
      throw new NotAuthorizedDto(new Failure(Failure.Messages.AUTHORIZATION_FAILURE));
    }
  }


  public AuthLoginResponseDto refresh(@NonNull String refreshToken) throws ExecutionException, InterruptedException {
    if (jwtProvider.validateRefreshToken(refreshToken)) {
      final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
      final String email = claims.getSubject();
      final String saveRefreshToken = refreshStorage.get(email);
      if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
        final User user = userService.getUserByEmail(email).get().getOrElseThrow(failure -> new NotAuthorizedDto(failure));
        final String accessToken = jwtProvider.generateAccessToken(user);
        final String newRefreshToken = jwtProvider.generateRefreshToken(user);
        refreshStorage.put(user.getEmail(), newRefreshToken);
        return new AuthLoginResponseDto(accessToken, newRefreshToken);
      }
    }
    throw new NotAuthorizedDto(new Failure(Failure.Messages.AUTHORIZATION_FAILURE));
  }

  public JwtAuthentication getAuthInfo() {
    return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
  }


}