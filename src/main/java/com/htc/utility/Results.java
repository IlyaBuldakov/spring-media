package com.htc.utility;

import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;

/**
 * Вспомогательный класс для работы с возвращаемыми значениями.
 */
public final class Results {
  public static <T> CompletableFuture<Either<Failure, T>> succeed(T value) {
    return CompletableFuture.completedFuture(Either.right(value));
  }

  public static <T> CompletableFuture<Either<Failure, T>> fail(Failure failure) {
    return CompletableFuture.completedFuture(Either.left(failure));
  }

  private Results() {}
}
