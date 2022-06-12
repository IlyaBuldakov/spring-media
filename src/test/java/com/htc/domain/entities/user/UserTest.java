package com.htc.domain.entities.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.javafaker.Faker;
import com.htc.domain.entities.failures.InvalidValue;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Locale;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserTest {
  private static final Faker faker = Faker.instance(new Locale("ru"));

  private static final int validId = 1;
  private static final int invalidId = -1;

  private static final String validName = faker.name().fullName();
  private static final String invalidName = "";

  private static final String validEmail = faker.internet().emailAddress();
  private static final String invalidEmail = "";

  private static final String validPassword = "abcdEFG1234";

  private static final String validImage = faker.lorem().characters(40);
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
    assertThat(modifiers).containsOnly(true);
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
    "",                      // пустой пароль
    "1aA",                   // длина пароля меньше 8
    "1234567890123456789aA", // длина пароля больше 20
    "Пароль1Пароль",         // не латинские символы
    "abcdefg1",              // только нижний регистр
    "ABCDEFG1",              // только верхний регистр
    "12345678",              // только цифры
    "abcdEFGH",              // не содержит цифры
  })
  void create_InvalidPassword_ShouldReturnInvalidValue(String invalidPassword) {
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