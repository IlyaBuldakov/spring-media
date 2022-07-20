package com.htc.domain.repositories;

import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.task.Task;
import io.vavr.control.Either;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface TasksRepository {

    CompletableFuture<Either<Failure, Void>> create(String name,
                                                    ContentType type,
                                                    String description,
                                                    int author,
                                                    int executor,
                                                    LocalDate dateExpired);

    CompletableFuture<Either<Failure, Task>> getById(int id);

    CompletableFuture<Either<Failure, List<Task>>> getAll();

    CompletableFuture<Either<Failure, Void>> update(int id,
                                                    String name,
                                                    ContentType type,
                                                    String description,
                                                    int author,
                                                    int executor,
                                                    LocalDate dateExpired);

    CompletableFuture<Either<Failure, Void>> deleteById(int id);
}
