package com.htc.domain.usecases;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import lombok.NonNull;

/**
 * Сценарий использования.
 *
 * @param <ParamsT> Параметры сценария.
 * @param <ResultT> Результат работы сценария.
 */
public interface UseCase<ParamsT, ResultT> {
  /**
   * Выполнить сценарий использования.
   *
   * @param param Параметр сценария использования.
   * @return Результат выполнения сценария использования.
   */
  Either<Failure, ResultT> execute(@NonNull ParamsT param);

  /**
   * Выполнить сценарий использования с проверкой прав пользователя,
   * выполняющего сценарий использования.
   *
   * @param subjectId Идентификатор пользователя, выполняющего сценарий использования.
   * @param params Параметры сценария использования.
   * @return Ошибка или результат выполнения сценария использования.
   */
  Either<Failure, ResultT> execute(Id subjectId, ParamsT params);

  /**
   * Параметр-заглушка для сценариев использования без параметров.
   */
  record NoParams() {}
}

