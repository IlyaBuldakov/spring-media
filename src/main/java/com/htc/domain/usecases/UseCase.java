package com.htc.domain.usecases;

import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;

/**
 * Сценарий использования.
 *
 * @param <ParamT> Параметры сценария.
 * @param <ResultT> Результат работы сценария.
 */
public interface UseCase<ParamT, ResultT> {
  /**
   * Выполнить сценарий использования.
   *
   * @param param Параметр сценария использования.
   * @return Результат выполнения сценария использования.
   */
  CompletableFuture<Either<Failure, ResultT>> execute(ParamT param);
}
