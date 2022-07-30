package com.htc.domain.usecases.contents;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.ContentsRepository;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Реализация сценария получения всего контента.
 */
@Component
@AllArgsConstructor
public class GetAllContent {

  /**
   * Поле для внедрения реализации из infrastructure layer.
   */
  ContentsRepository contentsRepository;

  /**
   * Метод сценария
   *
   * @return void.
   */
  public CompletableFuture<Either<Failure, List<Content>>> execute() {
    return contentsRepository.getAll();
  }
}
