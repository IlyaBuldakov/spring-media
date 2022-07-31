package finalproject.application.dto.failures;

import finalproject.domain.entities.failures.BadRequest;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.failures.NotAuthorized;
import finalproject.domain.entities.failures.NotFound;

/**
 * Бросает исключения в зависимости от типа ошибки.
 */
public class FailureConverter {

  /**
   * Статический метод преобразования.
   *
   * @param failure принимает ошибку из доменного слоя
   * @return Dto перехватываемых исключений
   */
  public static FailureDto convert(Failure failure) {
    if (failure instanceof BadRequest) {
      return new BadRequestDto(failure);
    }
    if (failure instanceof NotFound) {
      return new NotFoundDto(failure);
    }
    if (failure instanceof NotAuthorized) {
      return new NotAuthorizedDto(failure);
    }
    return new InternalServerErrorDto(failure);
  }
}
