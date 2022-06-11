package com.htc.core;

import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Вспомогательный класс для работы с возвращаемыми значениями репозитория.
 */
public abstract class EitherHelper<T> {
  public static <T> Future<Either<Failure, T>> goodRight(T value) {
    return CompletableFuture.completedFuture(Either.right(value));
  }

  public static <T> Future<Either<Failure, T>> badLeft(Failure failure) {
    return CompletableFuture.completedFuture(Either.left(failure));
  }
}
