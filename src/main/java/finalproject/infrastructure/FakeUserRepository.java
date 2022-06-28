//package finalproject.infrastructure;
//
//import finalproject.domain.entities.user.User;
//import finalproject.domain.repositories.UserRepository;
//import org.apache.commons.lang3.SerializationUtils;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//
//public class FakeUserRepository implements UserRepository {
//  static List<User> userList = new ArrayList<>();
//  static {
//    userList.add(null);
//  }
//
//
//
//
//
//  @Override
//  public <S extends User> S save(S user) {
//
//    if (user.getId() > 0) {
//      userList.set(user.getId(), SerializationUtils.clone(user));
//      return user;
//    }
//    user.setId(userList.size());
//    userList.add(SerializationUtils.clone(user));
//    return user;
//  }
//
//
//
//  @Override
//  public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
//    return null;
//  }
//
//
//  @Override
//  public Optional<User> findById(Integer id) {
//    if (id < userList.size() && userList.get(id) != null) {
//      return Optional.of(SerializationUtils.clone(userList.get(id)));
//    }
//    return Optional.empty();
//  }
//
//  @Override
//  public boolean existsById(Integer integer) {
//    return false;
//  }
//
//  @Override
//  public List<User> findAll() {
//    return userList.stream().filter(Objects::nonNull).toList();
//  }
//
//  @Override
//  public Iterable<User> findAllById(Iterable<Integer> integers) {
//    return null;
//  }
//
//  @Override
//  public long count() {
//    return 0;
//  }
//
//  @Override
//  public void deleteById(Integer id) {
//    if (id < userList.size() && userList.get(id) != null) {
//      userList.set(id, null);
//    }
//
//  }
//
//  @Override
//  public void delete(User entity) {
//
//  }
//
//  @Override
//  public void deleteAllById(Iterable<? extends Integer> integers) {
//
//  }
//
//  @Override
//  public void deleteAll(Iterable<? extends User> entities) {
//
//  }
//
//  @Override
//  public void deleteAll() {
//
//  }
//}
