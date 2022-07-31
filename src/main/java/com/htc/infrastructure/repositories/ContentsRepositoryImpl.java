package com.htc.infrastructure.repositories;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.failure.Failure;
import com.htc.domain.entities.failure.NotFound;
import com.htc.domain.repositories.ContentsRepository;
import com.htc.infrastructure.jpa.ContentsJpaRepository;
import com.htc.infrastructure.mappers.ContentMapper;
import com.htc.util.Results;
import io.vavr.control.Either;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

/**
 * Реализация репозитория для работы с контентом.
 */
@Component
@AllArgsConstructor
public class ContentsRepositoryImpl implements ContentsRepository {

  /**
   * JPA-репозиторий.
   */
  ContentsJpaRepository contentsJpaRepository;

  /**
   * Получение списка контента.
   *
   * @return Список контента.
   */
  @Override
  public CompletableFuture<Either<Failure, List<Content>>> getAll() {
    return Results.success(new ArrayList<>(contentsJpaRepository.findAll()));
  }

  /**
   * Создание контента.
   *
   * @param name          Имя контента.
   * @param type          Тип контента.
   * @param contentFormat Формат контента.
   * @param taskId        Идентификатор задачи.
   * @return void.
   */
  @Override
  public CompletableFuture<Either<Failure, Void>> create(int authorId, String name,
                                                         ContentType type,
                                                         Content.ContentFormat contentFormat,
                                                         String url, int taskId) {
    contentsJpaRepository.save(new ContentMapper(name, type, authorId, contentFormat, url, taskId));
    return Results.nullValue();
  }

  /**
   * Удаление контента по идентификатору.
   *
   * @param id Идентификатор контента.
   * @return void.
   */
  @Override
  public CompletableFuture<Either<Failure, Void>> deleteById(int id) {
    try {
      contentsJpaRepository.deleteById(id);
    } catch (EmptyResultDataAccessException exception) {
      return Results.fail(NotFound.CONTENT);
    }
    return Results.nullValue();
  }

  /**
   * Поиск URL контента, относящегося к задаче.
   *
   * @param taskId Идентификатор задачи.
   * @return Множество URL.
   */
  @Override
  public Set<String> findRelevantToTaskContentUrl(int taskId) {
    return contentsJpaRepository.findContentMappersByTaskId(taskId)
            .parallelStream().map(ContentMapper::getUrl).collect(Collectors.toSet());
  }
}
