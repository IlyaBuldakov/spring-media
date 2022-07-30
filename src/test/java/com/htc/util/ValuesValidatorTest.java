package com.htc.util;

import com.htc.domain.entities.failure.InvalidValuesContainer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

class ValuesValidatorTest {

    // Валидные данные пользователя.
    private final String ID = "1";
    private final String USERNAME = "Тестовый пользователь";
    private final String PASSWORD = "1aaaAAAaaa1";
    private final String EMAIL = "mail@mail.ru";
    private final String AVATAR = "SGVsbG9Xb3JsZA==";

    @Test
    void checkUserFields_IncorrectId_ShouldReturnInvalidValue() {
        var result = ValuesValidator.checkUserFields(
                "-10", USERNAME, PASSWORD, EMAIL, AVATAR);

        assertThat(result)
                .isNotNull()
                .isInstanceOf(InvalidValuesContainer.class);
    }

    @Test
    void checkUserFields_IncorrectName_ShouldReturnInvalidValue() {
        var result = ValuesValidator.checkUserFields(ID, "", PASSWORD,
                EMAIL, AVATAR);

        assertThat(result)
                .isNotNull()
                .isInstanceOf(InvalidValuesContainer.class);
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
        var result = ValuesValidator.checkUserFields(ID, USERNAME, testPassword,
                EMAIL, AVATAR);

        assertThat(result)
                .isNotNull()
                .isInstanceOf(InvalidValuesContainer.class);
    }

    @Test
    void checkUserFields_IncorrectEmail_ShouldReturnInvalidValue() {
        var result = ValuesValidator.checkUserFields(ID, USERNAME, PASSWORD,
                "213.com", AVATAR);

        assertThat(result)
                .isNotNull()
                .isInstanceOf(InvalidValuesContainer.class);
    }

    @Test
    void checkUserFields_IncorrectAvatar_ShouldReturnInvalidValue() {
        var result = ValuesValidator.checkUserFields(ID, USERNAME, PASSWORD,
                EMAIL, "");

        assertThat(result)
                .isNotNull()
                .isInstanceOf(InvalidValuesContainer.class);
    }
}