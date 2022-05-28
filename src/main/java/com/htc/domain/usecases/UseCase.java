package com.htc.domain.usecases;

import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;

import java.util.concurrent.Future;

/**
 * Класс сценария
 *
 * @param <ParamT>  Параметр сценария
 * @param <ResultT> Результат использования сценария
 * @author IlyaBuldakov
 */
public interface UseCase<ParamT, ResultT> {

    /**
     * Метод выполнения сценария
     *
     * @param param Параметр сценария
     * @return Результат выполнения сценария
     */
    Future<Either<Failure, ResultT>> execute(ParamT param);
}
