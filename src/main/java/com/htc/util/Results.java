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
   * @param result    Результат.
   * @param <ResultT> Тип результата.
   * @return Обертка {@link CompletableFuture} + {@link Either}.
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
   * @param failure    Ошибка.
   * @param <FailureT> Тип ошибки (лево).
   * @param <ResultT>  Тип результата (право).
   * @return Обертка {@link CompletableFuture} + {@link Either}.
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
   * @param result    Результат.
   * @param <ResultT> Тип результата.
   * @return Обертка {@link CompletableFuture}.
   */
  public static <ResultT> CompletableFuture<ResultT> single(
          ResultT result
  ) {
    return CompletableFuture.completedFuture(result);
  }

  /**
   * Обертка null значения.
   * Используется в методах, имеющих Void
   * в возвращаемом значении.
   *
   * @param <ResultT> Тип результата.
   * @return Обертка {@link CompletableFuture}.
   */
  public static <ResultT> CompletableFuture<Either<Failure, ResultT>> nullValue() {
    return CompletableFuture.completedFuture(Either.right(null));
  }
}
