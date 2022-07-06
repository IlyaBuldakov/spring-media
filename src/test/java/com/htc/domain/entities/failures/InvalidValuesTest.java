package com.htc.domain.entities.failures;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class InvalidValuesTest {
  @Test
  void twoConstructorExistsOnly() {
    var constructors = InvalidValues.class.getDeclaredConstructors();
    assertThat(constructors).hasSize(2);
  }

  @Test
  void firstConstructorHasMapParam() {
    var constructors = InvalidValues.class.getDeclaredConstructors();
    assertThat(constructors[0].getParameterCount()).isEqualTo(1);
    assertThat(constructors[0].getParameterTypes()).containsOnly(java.util.Map.class);
  }

  @Test
  void secondConstructorHasNoParams() {
    var constructors = InvalidValues.class.getDeclaredConstructors();
    assertThat(constructors[1].getParameterCount()).isEqualTo(0);
  }
}
