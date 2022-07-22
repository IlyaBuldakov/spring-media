package com.htc.domain.repositories;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.Content.Format;
import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ContentsRepository {

    CompletableFuture<Either<Failure, List<Content>>> getAll();

    CompletableFuture<Either<Failure, Void>> create(String name, ContentType type, Format format, int taskId);

    CompletableFuture<Either<Failure, Void>> deleteById(int id);
}
