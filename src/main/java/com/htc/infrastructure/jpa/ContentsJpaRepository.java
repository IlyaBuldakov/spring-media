package com.htc.infrastructure.jpa;

import com.htc.infrastructure.mappers.ContentMapper;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA-репозиторий для работы с контентом.
 */
public interface ContentsJpaRepository extends JpaRepository<ContentMapper, Integer> {
}
