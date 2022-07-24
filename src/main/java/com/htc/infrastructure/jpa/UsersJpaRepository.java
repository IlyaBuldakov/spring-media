package com.htc.infrastructure.jpa;

import com.htc.infrastructure.mappers.UserMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * JPA-репозиторий для работы с пользователями.
 */
public interface UsersJpaRepository extends JpaRepository<UserMapper, Integer> {

    Optional<UserMapper> findUserMapperByEmail(String email);
}

