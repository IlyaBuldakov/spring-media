package com.htc.domain.entities.user;

import com.htc.domain.entities.failures.InvalidValue;
import org.junit.jupiter.api.Test;
import com.htc.util.Users;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Modifier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserTest {

    private static final User TEST_USER = Users.createTestUser();

    @Test
    void hasOnlyOneConstructor_AndThisConstructorShouldBePrivate() {
        User user = User.create(
                TEST_USER.getId(), TEST_USER.getName(), TEST_USER.getPassword(),
                TEST_USER.getEmail(), TEST_USER.getAvatar(), TEST_USER.getRole()).get();

        var userClass = user.getClass();
        var userConstructors = userClass.getDeclaredConstructors();
        assertThat(userConstructors).hasSize(1);
        assertThat(userConstructors[0].getModifiers()).isEqualTo(Modifier.PRIVATE);
    }

    @Test
    void create_InvalidId_ShouldReturnFailure() {
        var user = User.create(-10, TEST_USER.getName(), TEST_USER.getPassword(),
                TEST_USER.getEmail(), TEST_USER.getAvatar(), TEST_USER.getRole()).getLeft();

        assertThat(user)
                .isNotNull()
                .isInstanceOf(InvalidValue.class);
    }

    @Test
    void create_InvalidName_ShouldReturnFailure() {
        var user = User.create(TEST_USER.getId(), "", TEST_USER.getPassword(),
                TEST_USER.getEmail(), TEST_USER.getAvatar(), TEST_USER.getRole()).getLeft();

        assertThat(user)
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
    void create_InvalidPassword_ShouldReturnFailure(String testPassword) {
        var user = User.create(TEST_USER.getId(), TEST_USER.getName(), testPassword,
                TEST_USER.getEmail(), TEST_USER.getAvatar(), TEST_USER.getRole()).getLeft();

        assertThat(user)
                .isNotNull()
                .isInstanceOf(InvalidValue.class);
    }

    @Test
    void create_InvalidEmail_ShouldReturnFailure() {
        var user = User.create(TEST_USER.getId(), TEST_USER.getName(), TEST_USER.getPassword(),
                "213.com", TEST_USER.getAvatar(), TEST_USER.getRole()).getLeft();

        assertThat(user)
                .isNotNull()
                .isInstanceOf(InvalidValue.class);
    }

    @Test
    void create_InvalidAvatar_ShouldReturnFailure() {
        var user = User.create(TEST_USER.getId(), TEST_USER.getName(), TEST_USER.getPassword(),
                TEST_USER.getEmail(), "", TEST_USER.getRole()).getLeft();

        assertThat(user)
                .isNotNull()
                .isInstanceOf(InvalidValue.class);
    }
}