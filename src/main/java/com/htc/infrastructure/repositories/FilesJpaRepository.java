package com.htc.infrastructure.repositories;

import com.htc.infrastructure.mappers.FileMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesJpaRepository extends JpaRepository<FileMapper, Integer> {
}
