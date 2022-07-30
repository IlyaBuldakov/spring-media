package finalproject.infrastructure.repositories;

import finalproject.domain.entities.filedocuments.FileDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для файлов.
 */
@Repository
public interface FileDocumentRepository extends JpaRepository<FileDocument, Integer> {
  List<FileDocument> findByTaskId(int id);
}
