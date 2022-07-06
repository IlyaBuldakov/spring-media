package com.htc.domain.entities.failures;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class InvalidValueParamTest {
  @Test
  void oneConstructorExistsOnly() {
    var constructors = InvalidValueParam.class.getDeclaredConstructors();
    assertThat(constructors).hasSize(1);
  }

  /**
   * Проверка полей конструктора класса, наследуемого от Enum.
   *
   * <p>В начало массива параметров конструктора неявно добавляется два параметра,
   * смотри конструктор {@link Enum}</p>
   */
  @Test
  void classIsEnumAndConstructorHasStringParam() {
    var constructors = InvalidValueParam.class.getDeclaredConstructors();
    assertThat(InvalidValueParam.class.isEnum()).isTrue();
    assertThat(constructors[0].getParameterCount()).isEqualTo(3);
    assertThat(constructors[0].getParameterTypes())
            .containsSequence(String.class, int.class, String.class);
  }
}
