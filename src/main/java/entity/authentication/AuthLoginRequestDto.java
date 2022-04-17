package entity.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@AllArgsConstructor
public class AuthLoginRequestDto {
    /**
     *
     */
    @Getter String email;
    /**
     *
     */
    @Getter String password;
}
