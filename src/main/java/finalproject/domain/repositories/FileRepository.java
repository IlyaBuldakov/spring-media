package finalproject.domain.repositories;

import finalproject.domain.entities.file.File;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CrudRepository<File, Integer> {
}
