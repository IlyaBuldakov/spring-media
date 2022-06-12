package com.htc.utility;

import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;

/**
 * Вспомогательный класс для работы с возвращаемыми значениями.
 */
public final class EitherHelper<T> {

  private EitherHelper() {}

  public static <T> CompletableFuture<Either<Failure, T>> goodRight(T value) {
    return CompletableFuture.completedFuture(Either.right(value));
  }

  public static <T> CompletableFuture<Either<Failure, T>> badLeft(Failure failure) {
    return CompletableFuture.completedFuture(Either.left(failure));
  }
}
