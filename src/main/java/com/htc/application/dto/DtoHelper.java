package com.htc.application.dto;

import com.htc.application.dto.errors.InternalServerErrorResponse;
import com.htc.domain.entities.ResponseConvertable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Вспомогательный класс для представлений (DTO).
 */
public class DtoHelper {

  /**
   * Создание списка представления из списка
   * соответствующих сущностей при помощи Reflection API.
   *
   * @param list        Список сущностей {@link ResponseConvertable}.
   * @param dtoClass    Класс DTO представления
   *                    к которому хотим привести список сущностей.
   * @param entityClass Класс сущности, из которой будем производить конвертацию.
   * @param <DtoT>      Ограничения на вход в метод DTO
   *                    представлений, которые точно являются DTO.
   * @param <EntityT>   Ограничение на вход в метод сущностей,
   *                    которые точно способны конвертироваться в DTO
   *                    (см. интерфейс {@link ResponseConvertable})
   * @return Список представлений (DTO) {@link BaseResponse}.
   */
  public static <DtoT extends BaseResponse, EntityT extends ResponseConvertable> List<DtoT>
      createFromEntityList(
          List<? extends EntityT> list,
          Class<? extends DtoT> dtoClass,
          Class<? extends EntityT> entityClass) {
    List<DtoT> result = new ArrayList<>();
    for (ResponseConvertable entity : list) {
      try {
        result.add(dtoClass.getConstructor(entityClass).newInstance(entity));
      } catch (Exception exception) {
        throw new InternalServerErrorResponse();
      }
    }
    return result;
  }
}
