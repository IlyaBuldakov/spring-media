package finalproject.infrastructure.repositories;

import finalproject.domain.entities.content.Content;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Репозиторий контента.
 */

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {
  List<Content> findByIsPublished(Boolean bool);

  List<Content> findByTaskId(int id);

  Optional<Content> findOneByUrl(String url);
}
