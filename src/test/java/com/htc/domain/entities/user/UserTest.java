package com.htc.domain.entities.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.javafaker.Faker;
import com.htc.domain.entities.failures.InvalidValues;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Locale;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserTest {
  private static final Faker faker = Faker.instance(new Locale("ru"));
  private static final int validId = 113;
  private static final int invalidId = -113;
  private static final String validName = "test Имя._0";
  private static final String invalidName = " ";
  private static final String  validPassword = "gTeggstiag5";
  private static final String  invalidPassword = "gTeggstiag";
  private static final String validEmail =  faker.internet().emailAddress();
  private static final String invalidEmail =  faker.internet().emailAddress() + "@";
  private static final String validAvatar = faker.lorem().characters(40) + "==";
  private static final String invalidAvatar = "(^_^)";
  private static final Role role = Role.ADMIN;

  @Test
  void existsOneConstructorOnlyAndConstructorIsPrivate() {
    var constructors = User.class.getDeclaredConstructors();
    var modifiers = Arrays.stream(constructors)
            .map(c -> Modifier.isPrivate(c.getModifiers())).toList();
    assertThat(constructors).hasSize(1);
    assertThat(modifiers).containsOnly(true);
  }

  @Test
  // метод_условие(состояние системы)_ожидаемый результат
  void create_ValidValues_ShouldCreateUser() {
    var result = User.add(
            validId,
            validName,
            validPassword,
            validEmail,
            validAvatar,
            role
            ).get();
    assertThat(result).isInstanceOf(User.class);
  }

  @Test
  void create_InvalidId_ShouldReturnInvalidValue() {
    var result = User.add(
            invalidId,
            validName,
            validPassword,
            validEmail,
            validAvatar,
            role
    ).getLeft();
    assertThat(result).isInstanceOf(InvalidValues.class);
  }

  @Test
  void create_InvalidName_ShouldReturnInvalidValue() {
    var result = User.add(
            validId,
            invalidName,
            validPassword,
            validEmail,
            validAvatar,
            role
    ).getLeft();
    assertThat(result).isInstanceOf(InvalidValues.class);
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "",
      "1aA",
      "01234567890123456789aA",
      "Мультипароль90210",
      "abcabc4",
      "ABCABC5",
      "12345678",
      "abcdEFGH"
  })
  void create_InvalidPassword_ShouldReturnInvalidValue() {
    var result = User.add(
            validId,
            validName,
            invalidPassword,
            validEmail,
            validAvatar,
            role
    ).getLeft();
    assertThat(result).isInstanceOf(InvalidValues.class);
  }

  @Test
  void create_InvalidEmail_ShouldReturnInvalidValue() {
    var result = User.add(
            validId,
            validName,
            validPassword,
            invalidEmail,
            validAvatar,
            role
    ).getLeft();
    assertThat(result).isInstanceOf(InvalidValues.class);
  }

  @Test
  void create_InvalidImage_ShouldReturnInvalidValue() {
    var result = User.add(
            validId,
            validName,
            validPassword,
            validEmail,
            invalidAvatar,
            role
    ).getLeft();
    assertThat(result).isInstanceOf(InvalidValues.class);
  }
}
