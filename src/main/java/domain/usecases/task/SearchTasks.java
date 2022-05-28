package domain.usecases.task;

import domain.entities.failures.Failure;
import domain.entities.tasks.Task;
import domain.entities.user.User;
import domain.repositories.TaskRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;

/**
 * Сценарий получения списка задач в соответствии с запросом.
 */
@AllArgsConstructor
public final class SearchTasks implements UseCase<SearchTasks.Query, Iterable<Task>> {
  /**
   * Параметры запроса поиска задачи.
   *
   * @param query Строка поиска.
   * @param user  Пользователя.
   */
  public record Query(String query, User user) {
  }

  private final TaskRepository repository;

  @Override
  public Future<Either<Failure, Iterable<Task>>> execute(SearchTasks.Query query) {
    return query.user() == null
            ? repository.search(query.query())
            : repository.search(query.query(), query.user());
  }
}
