package finalproject.infrastructure.repositories;

import finalproject.domain.entities.content.Content;
import finalproject.domain.entities.filedocuments.FileDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


/**
 * Репозиторий контента.
 */
public interface ContentRepository extends JpaRepository<Content, Integer> {
  List<Content> findByIsPublished(Boolean bool);
  List<Content> findByTaskId(int id);
  Optional<Content> findOneByUrl(String url);
}
