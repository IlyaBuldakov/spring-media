package com.htc.infrastructure.repositories;

import com.htc.infrastructure.mappers.ContentMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentsJpaRepository extends JpaRepository<ContentMapper, Integer> {
}
