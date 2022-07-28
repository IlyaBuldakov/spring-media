package finalproject.infrastructure.repositories;

import finalproject.domain.entities.content.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * Репозиторий контента.
 */
public interface ContentRepository extends JpaRepository<Content, Integer> {
  List<Content> findByIsPublished(Boolean bool);
}
