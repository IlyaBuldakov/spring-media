package domain.repositories;

import domain.entities.User;

public interface UserRepository {
  void create(User user);
  User get(int id);
  void update(User user);
  void delete(int id);
}
