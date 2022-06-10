package finalproject.application.services;

import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.user.User;
import finalproject.domain.repositories.UserRepository;
import io.vavr.control.Either;

import java.util.List;
import java.util.concurrent.Future;

public interface UserService {

  Future<Either<Failure, Integer>> createNewUser(User user);
  Future<Either<Failure, Void>> editUser(User user);
  Future<Either<Failure, Void>> deleteUser(User user);
  Future<Either<Failure, User>> getUserById(int id);
  Future<Either<Failure, List<User>>> getAllUsers();
  Future<Either<Failure, List<User>>> getUsersByQuery(String query);

}

/*
long	count()
Returns the number of entities available.
void	delete(T entity)
Deletes a given entity.
void	deleteAll()
Deletes all entities managed by the repository.
void	deleteAll(Iterable<? extends T> entities)
Deletes the given entities.
void	deleteAllById(Iterable<? extends ID> ids)
Deletes all instances of the type T with the given IDs.
void	deleteById(ID id)
Deletes the entity with the given id.
boolean	existsById(ID id)
Returns whether an entity with the given id exists.
Iterable<T>	findAll()
Returns all instances of the type.
Iterable<T>	findAllById(Iterable<ID> ids)
Returns all instances of the type T with the given IDs.
Optional<T>	findById(ID id)
Retrieves an entity by its id.
<S extends T>
S	save(S entity)
Saves a given entity.
<S extends T>
Iterable<S>	saveAll(Iterable<S> entities)
Saves all given entities.

 */