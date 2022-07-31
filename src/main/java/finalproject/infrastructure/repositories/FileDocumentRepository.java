package finalproject.infrastructure.repositories;

import finalproject.domain.entities.filedocuments.FileDocument;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для файлов.
 */
@Repository
public interface FileDocumentRepository extends JpaRepository<FileDocument, Integer> {
  List<FileDocument> findByTaskId(int id);

  Optional<FileDocument> findOneByUrl(String url);
}
