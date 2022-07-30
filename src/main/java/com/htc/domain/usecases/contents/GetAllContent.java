package com.htc.domain.usecases.contents;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.failure.Failure;
import com.htc.domain.repositories.ContentsRepository;
import io.vavr.control.Either;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Реализация сценария получения всего контента.
 */
@Component
@AllArgsConstructor
public class GetAllContent {

  ContentsRepository contentsRepository;

  /**
   * Метод сценария.
   *
   * @return void.
   */
  public CompletableFuture<Either<Failure, List<Content>>> execute() {
    return contentsRepository.getAll();
  }
}
