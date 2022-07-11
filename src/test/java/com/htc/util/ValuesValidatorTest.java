package com.htc.util;

import com.htc.domain.entities.failures.InvalidValue;
import com.htc.domain.entities.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author IlyaBuldakov
 */
class ValuesValidatorTest {

    private final static User TEST_USER = Users.createTestUser();

    @Test
    void checkUserFields_IncorrectId_ShouldReturnInvalidValue() {
        var result = ValuesValidator.checkUserFields(
                -10, TEST_USER.getName(), TEST_USER.getPassword(),
                TEST_USER.getEmail(), TEST_USER.getAvatar());

        assertThat(result)
                .isNotNull()
                .isInstanceOf(InvalidValue.class);
    }

    @Test
    void checkUserFields_IncorrectName_ShouldReturnInvalidValue() {
        var result = ValuesValidator.checkUserFields(TEST_USER.getId(), "", TEST_USER.getPassword(),
                TEST_USER.getEmail(), TEST_USER.getAvatar());

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
        var result = ValuesValidator.checkUserFields(TEST_USER.getId(), TEST_USER.getName(), testPassword,
                TEST_USER.getEmail(), TEST_USER.getAvatar());

        assertThat(result)
                .isNotNull()
                .isInstanceOf(InvalidValue.class);
    }

    @Test
    void checkUserFields_IncorrectEmail_ShouldReturnInvalidValue() {
        var result = ValuesValidator.checkUserFields(TEST_USER.getId(), TEST_USER.getEmail(), TEST_USER.getPassword(),
                "213.com", TEST_USER.getAvatar());

        assertThat(result)
                .isNotNull()
                .isInstanceOf(InvalidValue.class);
    }

    @Test
    void checkUserFields_IncorrectAvatar_ShouldReturnInvalidValue() {
        var result = ValuesValidator.checkUserFields(TEST_USER.getId(), TEST_USER.getName(), TEST_USER.getPassword(),
                TEST_USER.getEmail(), "");

        assertThat(result)
                .isNotNull()
                .isInstanceOf(InvalidValue.class);
    }
}