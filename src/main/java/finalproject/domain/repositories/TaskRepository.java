package finalproject.domain.repositories;

import finalproject.domain.entities.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<User, Integer> {
}
