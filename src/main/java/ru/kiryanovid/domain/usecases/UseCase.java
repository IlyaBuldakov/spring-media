package ru.kiryanovid.domain.usecases;

import ru.kiryanovid.domain.entity.errors.Failure;
import io.vavr.control.Either;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

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