package finalproject.domain.repositories;

import finalproject.domain.entities.Comment;
import finalproject.domain.entities.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
