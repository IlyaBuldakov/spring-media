package finalproject.infrastructure.repositories;

import finalproject.domain.entities.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий комментариев.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
  List<Comment> findByTaskId(int id);
}
