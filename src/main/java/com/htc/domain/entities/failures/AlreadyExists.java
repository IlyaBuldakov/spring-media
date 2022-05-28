package com.htc.domain.entities.failures;

/**
 * Уже существует
 *
 * @author IlyaBuldakov
 */
public class AlreadyExists extends Failure {
    public AlreadyExists(String message) {
        super(message);
    }
}
