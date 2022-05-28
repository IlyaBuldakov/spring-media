package domain.entities.user;

import domain.entities.failures.InvalidValue;
import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import util.Users;

import java.lang.reflect.Modifier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author IlyaBuldakov
 */
class UserTest {

    private static final int validId = 1;

    private static final String validName = "Ольга Бузова";

    private static final String validPassword = "1aaAbbBccC";

    private static final String validEmail = "olga.buzova@mail.ru";

    private static final byte[] validAvatar = Base64.decodeBase64("SGVsbG9Xb3JsZA==");

    private static final Role validRole = Users.getRandomTestRole();

    @Test
    void hasOnlyOneConstructor_AndThisConstructorShouldBePrivate() {
        User user = User.create(validId, validName, validPassword, validEmail, validAvatar, validRole).get();

        var userClass = user.getClass();
        var userConstructors = userClass.getDeclaredConstructors();
        assertThat(userConstructors).hasSize(1);
        assertThat(userConstructors[0].getModifiers()).isEqualTo(Modifier.PRIVATE);
    }

    @Test
    void create_InvalidId_ShouldReturnFailure() {
        var user = User.create(-10, validName, validPassword, validEmail, validAvatar, validRole).getLeft();

        assertThat(user)
                .isNotNull()
                .isInstanceOf(InvalidValue.class);
    }

    @Test
    void create_InvalidName_ShouldReturnFailure() {
        var user = User.create(validId, "", validPassword, validEmail, validAvatar, validRole).getLeft();

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
        var user = User.create(validId, validName, testPassword, validEmail, validAvatar, validRole).getLeft();

        assertThat(user)
                .isNotNull()
                .isInstanceOf(InvalidValue.class);
    }

    @Test
    void create_InvalidEmail_ShouldReturnFailure() {
        var user = User.create(validId, validName, validPassword, "213.com", validAvatar, validRole).getLeft();

        assertThat(user)
                .isNotNull()
                .isInstanceOf(InvalidValue.class);
    }

    @Test
    void create_InvalidAvatar_ShouldReturnFailure() {
        var user = User.create(validId, validName, validPassword, validEmail, new byte[]{1, 2, 3}, validRole).getLeft();

        assertThat(user)
                .isNotNull()
                .isInstanceOf(InvalidValue.class);
    }
}