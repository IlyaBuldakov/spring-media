package finalproject.infrastructure.repositories;

import finalproject.domain.entities.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Репозиторий пользователей.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findOneByEmail(String email);
}
