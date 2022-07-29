package finalproject.infrastructure.repositories;


import finalproject.domain.entities.task.Task;
import finalproject.domain.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий задач.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
  List<Task> findByAuthor(User user);
  List<Task> findByContentMaker(User user);
}
