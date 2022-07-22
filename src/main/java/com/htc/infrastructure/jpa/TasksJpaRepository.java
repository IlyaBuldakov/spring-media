package com.htc.infrastructure.jpa;

import com.htc.infrastructure.mappers.TaskMapper;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA-репозиторий для работы с задачами.
 */
public interface TasksJpaRepository extends JpaRepository<TaskMapper, Integer> {
}
