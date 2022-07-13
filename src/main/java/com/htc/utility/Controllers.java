package com.htc.utility;

import com.htc.application.dto.failures.BadRequestResponse;
import com.htc.application.dto.failures.InternalServerErrorResponse;
import com.htc.application.dto.failures.NotFoundResponse;
import com.htc.application.dto.failures.UnauthorizedResponse;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.entities.failures.Unauthorized;
import com.htc.domain.usecases.UseCase;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * Вспомогательный класс для обработки запросов пользователя в контроллерах
 * слоя приложения.
 */
public class Controllers {
  /**
   * Обработать запрос пользователя.
   *
   * @param useCase Сценарий использования.
   * @param params Параметры сценария использования.
   * @param entityToDtoConverter Метод, преобразующий сущность доменного слоя
   *                             в сущность слоя приложения.
   * @return Результат обработки запроса пользователя.
   */
  public static <ParamsT, EntityT, DtoT> CompletableFuture<DtoT> handleRequest(
      UseCase<ParamsT, EntityT> useCase,
      ParamsT params,
      Function<EntityT, DtoT> entityToDtoConverter
  ) {
    return useCase.execute(params)
        .thenApply(either -> either
            .map(entityToDtoConverter)
            .getOrElseThrow(failure -> switch (failure) {
              case InvalidValues invalidValues -> new BadRequestResponse(invalidValues);
              case Unauthorized ignored -> new UnauthorizedResponse(failure);
              case NotFound ignored -> new NotFoundResponse(failure);
              default -> new InternalServerErrorResponse(failure);
            }));
  }

  private Controllers() {}
}
