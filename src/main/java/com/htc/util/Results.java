package com.htc.util;

import com.htc.domain.entities.failure.Failure;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;

/**
 * Вспомогательный класс для упрощения кода,
 * использующего обертку {@link CompletableFuture} + {@link Either}.
 */
public class Results {

  /**
   * Обертка в случае успеха.
   *
   * @param result Результат.
   *
   * @return Обертка {@link CompletableFuture} + {@link Either}.
   * @param <ResultT> Тип результата.
   */
  public static <ResultT>
  CompletableFuture<Either<Failure, ResultT>> success(
          ResultT result
  ) {
    return CompletableFuture.completedFuture(Either.right(result));
  }

  /**
   * Обертка в случае возникновения ошибки.
   *
   * @param failure Ошибка.
   *
   * @return Обертка {@link CompletableFuture} + {@link Either}.
   * @param <FailureT> Тип ошибки (лево).
   * @param <ResultT> Тип результата (право).
   */
  public static <FailureT, ResultT>
  CompletableFuture<Either<FailureT, ResultT>> fail(
          FailureT failure
  ) {
    return CompletableFuture.completedFuture(Either.left(failure));
  }

  /**
   * Обертка единственного результата.
   * (без {@link Either}).
   *
   * @param result Результат.
   *
   * @return Обертка {@link CompletableFuture}.
   * @param <ResultT> Тип результата.
   */
  public static <ResultT> CompletableFuture<ResultT> single(
          ResultT result
  ) {
    return CompletableFuture.completedFuture(result);
  }

  /**
   * Обертка null значения.
   *
   * @return Обертка {@link CompletableFuture}.
   * @param <ResultT> Тип результата.
   */
  public static <ResultT> CompletableFuture<Either<Failure, ResultT>> nullValue() {
    return CompletableFuture.completedFuture(Either.right(null));
  }
}
