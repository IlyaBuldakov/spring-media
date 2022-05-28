package com.htc.domain.entities.failures;

/**
 * Сущность уже сушествует.
 */
public class AlreadyExists extends Failure {

  public AlreadyExists(String message) {
    super(message);
  }
}
