package domain.entities.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Запрос на авторизацию
 */
@AllArgsConstructor
public class AuthLoginRequestDto {

   /**
    * @return email пользователя
    */
   private @Getter String email;

   /**
    * @return password пользователя
    */
   private @Getter String password;

}
