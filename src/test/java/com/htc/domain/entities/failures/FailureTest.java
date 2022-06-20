package com.htc.domain.entities.failures;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class FailureTest {
  @Test
  void onlyOneConstructorExistsAndConstructorHasOnlyOneStringParameter() {
    // Arrange
    var constructors = Failure.class.getDeclaredConstructors();

    // Assert
    assertThat(constructors).hasSize(1);
    assertThat(constructors[0].getParameterCount()).isEqualTo(1);
    assertThat(constructors[0].getParameterTypes()).containsOnly(String.class);
  }
}