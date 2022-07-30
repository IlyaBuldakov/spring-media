package com.htc.infrastructure.jpa;

import com.htc.infrastructure.mappers.FileMapper;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA-репозиторий для работы с файлами.
 */
public interface FilesJpaRepository extends JpaRepository<FileMapper, Integer> {

  Set<FileMapper> findFileMappersByTaskId(int id);
}
