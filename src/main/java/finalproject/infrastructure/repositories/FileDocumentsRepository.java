package finalproject.infrastructure.repositories;

import finalproject.domain.entities.filedocuments.FileDocument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для файлов.
 */
@Repository
public interface FileDocumentsRepository extends CrudRepository<FileDocument, Integer> {
}
