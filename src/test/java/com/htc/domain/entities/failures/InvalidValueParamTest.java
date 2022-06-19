package com.htc.domain.entities.failures;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class InvalidValueParamTest {
  @Test
  void oneConstructorExistsOnly() {
    var constructors = InvalidValueParam.class.getDeclaredConstructors();
    assertThat(constructors).hasSize(1);
  }
}
