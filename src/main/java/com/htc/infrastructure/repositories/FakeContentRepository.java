package com.htc.infrastructure.repositories;

import com.github.javafaker.Faker;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.ContentFormat;
import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.ContentRepository;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.springframework.stereotype.Component;

/**
 * Реализация репозитория контента с ненастоящими данными.
 */
@Component
public class FakeContentRepository implements ContentRepository {
  private static final Faker faker = Faker.instance(new Locale("ru"));
  private static final LocalDateTime date = LocalDateTime.now();
  private static final ContentFormat format = ContentFormat.JPG;
  private static final String urlFile = "test/url/file";
  private static final String urlPreview = "test/url/preview";
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
  private static final List<Content> contents = List.of(
      Content.upload(
          1,
          ContentType.PHOTO,
          faker.file().fileName(),
          date,
          users.get(0),
          format,
          urlFile,
          urlPreview,
          1
      ).get()
  );

  @Override
  public Future<Either<Failure, Content>> upload(Content content) {
    return null;
  }

  @Override
  public Future<Either<Failure, Void>> delete(int id) {
    return null;
  }

  @Override
  public Future<Either<Failure, Iterable<Content>>> getContentsByTaskId(int taskId) {
    var contentsByTaskId = contents.stream()
        .filter(u -> u.getTaskId() == taskId)
        .toList();
    return CompletableFuture.completedFuture(Either.right(contentsByTaskId));
  }

  @Override
  public Future<Either<Failure, Iterable<Content>>> getContentFeed() {
    return null;
  }
}
