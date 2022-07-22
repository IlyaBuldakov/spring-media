package com.htc.infrastructure.jpa;

import com.htc.infrastructure.mappers.FileMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * JPA-репозиторий для работы с файлами.
 */
public interface FilesJpaRepository extends JpaRepository<FileMapper, Integer> {

    Set<FileMapper> findFileMappersByTaskId(int id);
}
