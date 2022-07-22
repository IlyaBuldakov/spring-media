package finalproject.infrastructure.repositories;

import finalproject.domain.entities.file.File;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для файлов.
 */
@Repository
public interface FileRepository extends CrudRepository<File, Integer> {
}
