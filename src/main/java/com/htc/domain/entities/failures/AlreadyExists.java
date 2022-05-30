package com.htc.domain.entities.failures;

/**
 * Сущность уже существует.
 */
public class AlreadyExists extends Failure {
  public AlreadyExists(String message) {
    super(message);
  }
}
