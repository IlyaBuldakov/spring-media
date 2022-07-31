package com.htc.infrastructure.repositories;

import com.htc.domain.entities.User;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.repositories.UserRepository;
import com.htc.infrastructure.models.UserModel;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория пользователей.
 */
@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  Users users;

  @Override
  public User create(
      User.Name name,
      User.Email email,
      User.Password password,
      User.Image image,
      User.Role role) {
    var user = new UserModel(
        name.getValue(),
        email.getValue(),
        password.getValue(),
        image.getValue(),
        role);
    user = users.save(user);
    return user.toEntity();
  }

  @Override
  public User update(
      Id id,
      User.Name name,
      User.Email email,
      User.Password password,
      User.Image image,
      User.Role role
  ) {
    var user = new UserModel(
        id.getValue(),
        name.getValue(),
        email.getValue(),
        password.getValue(),
        image.getValue(),
        role
    );
    user = users.save(user);
    return user.toEntity();
  }

  @Override
  public void delete(Id id) {
    users.deleteById(id.getValue());
  }

  @Override
  public Optional<User> get(Id id) {
    return users
        .findById(id.getValue())
        .map(UserModel::toEntity);
  }

  @Override
  public Optional<User> get(User.Email email) {
    return users
        .findByEmail(email.getValue())
        .map(UserModel::toEntity);
  }

  @Override
  public Collection<User> getAll() {
    return users
        .findAll()
        .stream()
        .map(UserModel::toEntity)
        .collect(Collectors.toList());
  }

  @Override
  public boolean exists(Id id) {
    return users.existsById(id.getValue());
  }

  @Override
  public boolean exists(User.Email email) {
    return users.existsByEmail(email.getValue());
  }

  /**
   * ORM для доступа к данным пользователей в СУБД.
   */
  public interface Users extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findByEmail(String email);

    boolean existsByEmail(String email);
  }
}