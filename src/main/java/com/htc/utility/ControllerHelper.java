package com.htc.utility;

import com.htc.domain.usecases.UseCase;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * Вспомогательный класс для обработки запросов.
 */
public class ControllerHelper {
  private ControllerHelper() {}

  /**
   * Обработка запроса.
   *
   * @param useCase сценарий использования
   * @param params параметры сценария
   * @param entityToDtoConverter преобразование сушности в представление этой сущности
   * @param <ParamsT> type тип параметра
   * @param <EntityT> type тип сущности
   * @param <DtoT> type тип представления сущности
   * @return result результат обработки запроса
   */
  public static <ParamsT, EntityT, DtoT> CompletableFuture<DtoT> customRequest(
          UseCase<ParamsT, EntityT> useCase,
          ParamsT params,
          Function<EntityT, DtoT> entityToDtoConverter
  ) {
    return useCase.execute(params)
            .thenApplyAsync(either -> either.map(entityToDtoConverter)
                    .getOrElseThrow(() -> CustomExceptionsHelper.getExceptionFromLeft(either)));
  }
}
