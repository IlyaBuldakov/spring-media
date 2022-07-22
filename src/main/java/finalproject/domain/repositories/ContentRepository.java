package finalproject.domain.repositories;

import finalproject.domain.entities.content.Content;
import org.springframework.data.repository.CrudRepository;

/**
 * Репозиторий контента.
 */
public interface ContentRepository extends CrudRepository<Content, Integer> {
}
