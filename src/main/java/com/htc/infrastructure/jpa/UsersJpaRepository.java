package com.htc.infrastructure.jpa;

import com.htc.infrastructure.mappers.UserMapper;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA-репозиторий для работы с пользователями.
 */
public interface UsersJpaRepository extends JpaRepository<UserMapper, Integer> {
}

