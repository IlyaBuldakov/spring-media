package com.htc.infrastructure.repositories;

import com.htc.infrastructure.mappers.UserMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author IlyaBuldakov
 */
@Repository
public interface UsersJpaRepository extends JpaRepository<UserMapper, Integer> {
}

