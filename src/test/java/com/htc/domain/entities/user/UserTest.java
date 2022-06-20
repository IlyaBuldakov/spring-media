package com.htc.domain.entities.user;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.htc.domain.entities.failures.InvalidValue;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserTest {

  private static final int validId = 1;
  private static final int invalidId = -1;

  private static final String validName = "Ivan";
  private static final String invalidName = "";

  private static final String validEmail = "ivan@test.ru";
  private static final String invalidEmail = "";

  private static final String validPassword = "Aa123fsdkk";
  private static final String invalidPassword = "";

  private static final String validImage =
      "0K3RgtC+INC90LUg0LjQt9C+0LHRgNCw0LbQtdC90LjQtSEg0J3QviDRgNCw0LHQvtGC0LDQtdGCKSk=";
  private static final String invalidImage = "";

  private static final Role role = Role.ADMIN;

  @Test
  void onlyOneConstructorExistsAndConstructorIsPrivate() {
    // Arrange
    var constructors = User.class.getDeclaredConstructors();
    var modifiers = Arrays.stream(constructors)
        .map(c -> Modifier.isPrivate(c.getModifiers()))
        .toList();

    // Assert
    assertThat(constructors).hasSize(1);
    assertThat(modifiers.get(0)).isTrue();
  }

  @Test
  void create_ValidValues_ShouldCreateUser() {
    // Act
    var result = User.create(
        validId,
        validName,
        validEmail,
        validPassword,
        validImage,
        role).get();

    // Assert
    assertThat(result).isInstanceOf(User.class);
  }

  @Test
  void create_InvalidId_ShouldReturnInvalidValue() {
    // Act
    var result = User.create(
        invalidId,
        validName,
        validEmail,
        validPassword,
        validImage,
        role).getLeft();

    // Assert
    assertThat(result).isInstanceOf(InvalidValue.class);
  }

  @Test
  void create_InvalidName_ShouldReturnInvalidValue() {
    // Act
    var result = User.create(
        validId,
        invalidName,
        validEmail,
        validPassword,
        validImage,
        role).getLeft();

    // Assert
    assertThat(result).isInstanceOf(InvalidValue.class);
  }

  @Test
  void create_InvalidEmail_ShouldReturnInvalidValue() {
    // Act
    var result = User.create(
        validId,
        validName,
        invalidEmail,
        validPassword,
        validImage,
        role).getLeft();

    // Assert
    assertThat(result).isInstanceOf(InvalidValue.class);
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "",                       // пустой пароль
      "1aA",                    // длина пароля меньше 8
      "123456789011121314Aa1",  // длина пароля больше 20
      "Пароль1Pass",            // не латинские символы
      "12345678a",              // только нижний регистр
      "12345678A",              // только верхний регистр
      "123456789",              // только цифры
      "abcdEFGH",                // не содержит цифры
  })

  @Test
  void create_InvalidPassword_ShouldReturnInvalidValue() {
    // Act
    var result = User.create(
        validId,
        validName,
        validEmail,
        invalidPassword,
        validImage,
        role).getLeft();

    // Assert
    assertThat(result).isInstanceOf(InvalidValue.class);
  }

  @Test
  void create_InvalidImage_ShouldReturnInvalidValue() {
    // Act
    var result = User.create(
        validId,
        validName,
        validEmail,
        validPassword,
        invalidImage,
        role).getLeft();

    // Assert
    assertThat(result).isInstanceOf(InvalidValue.class);
  }
}