package domain.usecases;

import domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.util.concurrent.Future;


/**
 * Сценарий использования.
 *
 * @param <ParamT> Параметры сценария.
 * @param <ResultT> Результат работы сценария.
 */
public interface UseCase<ParamT, ResultT> {
  Future<Either<Failure, ResultT>> execute(ParamT param);
}

