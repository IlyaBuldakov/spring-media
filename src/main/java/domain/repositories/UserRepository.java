package domain.repositories;

import domain.entities.failures.Failure;
import domain.entities.user.User;
import domain.entities.user.UserRole;
import io.vavr.control.Either;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Репозиторий пользователей
 *
 * @author IlyaBuldakov
 */
public interface UserRepository {

    /**
     * Создание пользователя
     *
     * @param user Пользователь
     */
    Future<Either<Failure, User>> create(User user);

    /**
     * Обновление данных пользователя
     *
     * @param user Пользователь
     */
    Future<Either<Failure, User>> update(User user);

    /**
     * Удаление пользователя
     *
     * @param id Идентификатор пользователя
     */
    Future<Either<Failure, Void>> delete(int id);

    /**
     * Получение пользователя
     *
     * @param id Идентификатор пользователя
     * @return Пользователь
     */
    Future<Either<Failure, User>> get(int id);

    /**
     * Получение списка всех пользователей
     *
     * @return Список пользователей
     */
    Future<Either<Failure, List<User>>> getAll();

    /**
     * Получение списка пользователей, соответствующих запросу
     *
     * @param query Запрос
     * @return Список пользователей
     */
    Future<Either<Failure, List<User>>> search(String query);

    /**
     * Получение списка пользователей, соответствующих запросу и роли
     *
     * @param query    Запрос
     * @param userRole Роль
     * @return Список пользователей
     */
    Future<Either<Failure, List<User>>> search(String query, UserRole userRole);


}
