package com.htc.infrastructure.repositories;

import com.htc.infrastructure.mappers.TaskMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksJpaRepository extends JpaRepository<TaskMapper, Integer> {
}