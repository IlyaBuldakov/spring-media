package com.htc.infrastructure.repositories;

import com.github.javafaker.Faker;
import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.CommentRepository;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.springframework.stereotype.Component;

/**
 * Реализация репозитория комментариев с ненастоящими данными.
 */
@Component
public class FakeCommentRepository implements CommentRepository {
  private static final Faker faker = Faker.instance(new Locale("ru"));
  private static final LocalDateTime date = LocalDateTime.now();
  private static final List<User> users = List.of(
      User.create(
          1,
          faker.name().fullName(),
          faker.internet().emailAddress(),
          faker.internet().password(8, 12),
          faker.lorem().characters(40),
          Role.ADMIN
      ).get()
  );
  private static final List<Comment> comments = List.of(
      Comment.create(
          1,
          date,
          users.get(0),
          "Комментарий тут",
          1
      ).get(),
      Comment.create(
          2,
          date,
          users.get(0),
          "Комментарий два",
          1
      ).get(),
      Comment.create(
          3,
          date,
          users.get(0),
          "Комментарий три",
          3
      ).get()
  );

  @Override
  public Future<Either<Failure, Comment>> create(Comment comment) {
    return null;
  }

  @Override
  public Future<Either<Failure, Iterable<Comment>>> getCommentsByTaskId(int taskId) {
    var commentsByTaskId = comments.stream()
        .filter(u -> u.getTaskId() == taskId)
        .toList();
    return CompletableFuture.completedFuture(Either.right(commentsByTaskId));
  }
}
