package com.htc.domain.usecases;

import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.util.concurrent.Future;

/**
 * Сценарий использования.
 *
 * @param <ParamT> - параметр сценария
 * @param <ResultT> - результат работы сценария
 */
public interface UseCase<ParamT, ResultT> {

  /**
   * Сценарий использования.
   *
   * @param param - параметр сценария использования
   * @return - результат работы сценария использования
   */
  Future<Either<Failure, ResultT>> execute(ParamT param);
}
