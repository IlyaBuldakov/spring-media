package finalproject.infrastructure.repositories;

import finalproject.domain.entities.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий задач.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
