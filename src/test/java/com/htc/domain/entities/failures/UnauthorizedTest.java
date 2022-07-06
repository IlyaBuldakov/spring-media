package com.htc.domain.entities.failures;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class UnauthorizedTest {
  @Test
  void oneConstructorExistsOnly() {
    var constructors = Unauthorized.class.getDeclaredConstructors();
    assertThat(constructors).hasSize(1);
  }

  /**
   * Проверка полей конструктора класса, наследуемого от Enum.
   *
   * <p>В начало массива параметров конструктора неявно добавляется два параметра,
   * смотри конструктор {@link Enum}</p>
   */
  @Test
  void classIsEnumAndConstructorHasHttpStatusAndStringParamsSequence() {
    var constructors = Unauthorized.class.getDeclaredConstructors();
    assertThat(Unauthorized.class.isEnum()).isTrue();
    assertThat(constructors[0].getParameterCount()).isEqualTo(4);
    assertThat(constructors[0].getParameterTypes())
            .containsSequence(String.class, int.class, HttpStatus.class, String.class);
  }
}
