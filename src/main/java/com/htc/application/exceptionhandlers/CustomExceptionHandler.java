package com.htc.application.exceptionhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Перехватчик исключений.
 */
public abstract class CustomExceptionHandler {
  protected final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);
}
