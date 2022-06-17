package com.htc.util;

import com.htc.domain.entities.failures.InvalidValue;
import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author IlyaBuldakov
 */
class ValuesValidatorTest {

    private static final int validId = 1;

    private static final String validName = "Ольга Бузова";

    private static final String validPassword = "1aaAbbBccC";

    private static final String validEmail = "olga.buzova@mail.ru";

    private static final byte[] validAvatar = Base64.decodeBase64("SGVsbG9Xb3JsZA==");

    @Test
    void checkUserFields_IncorrectId_ShouldReturnInvalidValue() {
        var result = ValuesValidator.checkUserFields(-10, validName, validPassword, validEmail, validAvatar);

        assertThat(result)
                .isNotNull()
                .isInstanceOf(InvalidValue.class);
    }

    @Test
    void checkUserFields_IncorrectName_ShouldReturnInvalidValue() {
        var result = ValuesValidator.checkUserFields(validId, "", validPassword, validEmail, validAvatar);

        assertThat(result)
                .isNotNull()
                .isInstanceOf(InvalidValue.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "aB1", // Не подходит по длине (< 8)
            "aB1aB1aB1aB1aB1aB1aB1aB1", // Не подходит по длине (> 20)
            "aBcAbCaBc", // Только буквы
            "abcabcab1", // Только нижний регистр
            "ABCABCAB1", // Только верхний регистр
            "1234567890", // Только цифры
            "ПарольНадежный123", // Не латинские символы
            "" // Пустое значение
    })
    void checkUserFields_IncorrectPassword_ShouldReturnInvalidValue(String testPassword) {
        var result = ValuesValidator.checkUserFields(validId, validName, testPassword, validEmail, validAvatar);

        assertThat(result)
                .isNotNull()
                .isInstanceOf(InvalidValue.class);
    }

    @Test
    void checkUserFields_IncorrectEmail_ShouldReturnInvalidValue() {
        var result = ValuesValidator.checkUserFields(validId, validName, validPassword, "213.com", validAvatar);

        assertThat(result)
                .isNotNull()
                .isInstanceOf(InvalidValue.class);
    }

    @Test
    void checkUserFields_IncorrectAvatar_ShouldReturnInvalidValue() {
        var result = ValuesValidator.checkUserFields(validId, validName, validPassword, validEmail, new byte[]{1, 2, 3});

        assertThat(result)
                .isNotNull()
                .isInstanceOf(InvalidValue.class);
    }
}