package com.htc.domain.usecases;

import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import org.springframework.stereotype.Component;

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
   * @return Результат выполения сценария использования.
   */
  Future<Either<Failure, ResultT>> execute(ParamT param);
}
