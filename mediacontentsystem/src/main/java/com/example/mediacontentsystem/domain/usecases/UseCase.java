package com.example.mediacontentsystem.domain.usecases;

import com.example.mediacontentsystem.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.util.concurrent.Future;

/**
 * Сценарий использования.
 *
 * @param <ResultT> Результат работы сценария.
 */
public interface UseCase<ResultT> {
  Future<Either<Failure, ResultT>> execute();
}
