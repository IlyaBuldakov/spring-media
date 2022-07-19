package finalproject.domain.repositories;

import finalproject.domain.entities.file.File;
import org.springframework.data.repository.CrudRepository;

public interface FileRepository extends CrudRepository<File, Integer> {
}
