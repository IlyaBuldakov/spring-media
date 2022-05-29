package com.htc.domain.entities.failures;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FailureTest {
  @Test
  void oneConstructorExistsOnlyAndConstructorHasOneStringParameterOnly() {
    var constructors = Failure.class.getDeclaredConstructors();
    assertThat(constructors).hasSize(1);
    assertThat(constructors[0].getParameterCount()).isEqualTo(1);
    assertThat(constructors[0].getParameterTypes()).containsOnly(String.class);
  }

  @Test
  void constructorInitializesMessage() {
    var message = "chchcherry bomb";
    var failure = new Failure(message) {};
    assertThat(failure.getMessage()).isEqualTo(message);
  }
}