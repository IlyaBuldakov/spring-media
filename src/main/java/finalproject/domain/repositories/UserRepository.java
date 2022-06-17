package finalproject.domain.repositories;

import finalproject.domain.entities.user.User;
import org.springframework.data.repository.CrudRepository;



public interface UserRepository extends CrudRepository<User, Integer> {
}
