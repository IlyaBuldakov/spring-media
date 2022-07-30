package com.htc.infrastructure.jpa;

import com.htc.infrastructure.mappers.CommentMapper;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA-репозиторий для работы с комментариями.
 */
public interface CommentsJpaRepository extends JpaRepository<CommentMapper, Integer> {
}
