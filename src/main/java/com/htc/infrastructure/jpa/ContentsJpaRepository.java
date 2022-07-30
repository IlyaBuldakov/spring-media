package com.htc.infrastructure.jpa;

import com.htc.infrastructure.mappers.ContentMapper;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA-репозиторий для работы с контентом.
 */
public interface ContentsJpaRepository extends JpaRepository<ContentMapper, Integer> {

  Set<ContentMapper> findContentMappersByTaskId(int id);
}
