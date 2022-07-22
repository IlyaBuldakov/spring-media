package com.htc.infrastructure.repositories;

import com.htc.infrastructure.mappers.FileMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface FilesJpaRepository extends JpaRepository<FileMapper, Integer> {

    Set<FileMapper> findFileMappersByTaskId(int id);
}
