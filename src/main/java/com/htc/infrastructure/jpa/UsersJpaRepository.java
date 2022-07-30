package com.htc.infrastructure.jpa;

import com.htc.infrastructure.mappers.UserMapper;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA-репозиторий для работы с пользователями.
 */
public interface UsersJpaRepository extends JpaRepository<UserMapper, Integer> {

  Optional<UserMapper> findUserMapperByEmail(String email);
}

