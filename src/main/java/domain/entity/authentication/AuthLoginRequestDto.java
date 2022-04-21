package domain.entity.authentication;

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
    @Getter private String email;
    /**
     *
     */
    @Getter private String password;
}
