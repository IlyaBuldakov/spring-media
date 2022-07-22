package com.htc.infrastructure.repositories;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.repositories.ContentsRepository;
import com.htc.infrastructure.mappers.ContentMapper;
import com.htc.infrastructure.jpa.ContentsJpaRepository;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@AllArgsConstructor
public class ContentsRepositoryImpl implements ContentsRepository {

    ContentsJpaRepository contentsJpaRepository;

    @Override
    public CompletableFuture<Either<Failure, List<Content>>> getAll() {
        return CompletableFuture.completedFuture(Either.right(
                new ArrayList<>(contentsJpaRepository.findAll())
        ));
    }

    @Override
    public CompletableFuture<Either<Failure, Void>> create(String name, ContentType type, Content.Format format, int taskId) {
        contentsJpaRepository.save(new ContentMapper(name, type, format, taskId));
        return null;
    }

    @Override
    public CompletableFuture<Either<Failure, Void>> deleteById(int id) {
        try {
            contentsJpaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            return CompletableFuture.completedFuture(Either.left(NotFound.CONTENT));
        }
        return CompletableFuture.completedFuture(Either.right(null));
    }
}
