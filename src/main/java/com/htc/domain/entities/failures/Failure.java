package com.htc.domain.entities.failures;

import lombok.Getter;

/**
 * Ошибка.
 */
public abstract class Failure {
  private final @Getter String message;

  public Failure(String message) {
    this.message = message;
  }

}
