package com.htc.domain.entities.failures;

/**
 * Некорректное значение параметра.
 */
public class InvalidValueParam extends Failure {
  public InvalidValueParam(String message) {
    super(message);
  }
}
