package finalproject.infrastructure.repositories;

import finalproject.domain.entities.filedocuments.FileDocuments;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для файлов.
 */
@Repository
public interface FileDocumentsRepository extends CrudRepository<FileDocuments, Integer> {
}
