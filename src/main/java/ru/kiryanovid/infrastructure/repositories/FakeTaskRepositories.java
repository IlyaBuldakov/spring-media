package ru.kiryanovid.infrastructure.repositories;

import com.github.javafaker.Faker;
import io.vavr.control.Either;
import org.springframework.stereotype.Component;
import ru.kiryanovid.domain.entity.task.Status;
import ru.kiryanovid.domain.entity.users.User;
import ru.kiryanovid.domain.entity.content.Content;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.entity.task.ContentType;
import ru.kiryanovid.domain.entity.task.Task;
import ru.kiryanovid.domain.entity.users.Role;
import ru.kiryanovid.domain.repositories.TaskRepositories;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Реализация репозитория задач с ненастоящими данными.
 */
@Component
public class FakeTaskRepositories implements TaskRepositories {
    private static final Faker faker = Faker.instance(new Locale("ru"));
    private static final List<Task> taskList = List.of(
            Task.create(
                    1,
                    faker.lorem().characters(50),
                    ContentType.AUDIO,
                    faker.lorem().characters(50),
                    Path.of(faker.file().fileName()),
                    User.create(
                            1,
                            faker.name().fullName(),
                            faker.internet().emailAddress(),
                            faker.internet().password(5, 17) + "1aA",
                            faker.lorem().characters(40),
                            Role.ADMIN
                    ).get(),
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(1),
                    new Content(),
                    List.of(),
                    Status.APPROVED
            ).get(),
            Task.create(
                    2,
                    faker.lorem().characters(50),
                    ContentType.AUDIO,
                    faker.lorem().characters(50),
                    Path.of(faker.file().fileName()),
                    User.create(
                            1,
                            faker.name().fullName(),
                            faker.internet().emailAddress(),
                            faker.internet().password(5, 17) + "1aA",
                            faker.lorem().characters(40),
                            Role.ADMIN
                    ).get(),
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(1),
                    new Content(),
                    List.of(),
                    Status.FEEDBACK
            ).get(),
            Task.create(
                    3,
                    faker.lorem().characters(50),
                    ContentType.AUDIO,
                    faker.lorem().characters(50),
                    Path.of(faker.file().fileName()),
                    User.create(
                            1,
                            faker.name().fullName(),
                            faker.internet().emailAddress(),
                            faker.internet().password(5, 17) + "1aA",
                            faker.lorem().characters(40),
                            Role.ADMIN
                    ).get(),
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(1),
                    new Content(),
                    List.of(),
                    Status.IN_WORK
            ).get()
    );
    @Override
    public Future<Either<Failure, Task>> create(Task task) {
        taskList.add(task);
        return CompletableFuture.completedFuture(Either.right(null));
    }

    @Override
    public Future<Either<Failure, Task>> update(Task task) {

        return null;
    }

    @Override
    public Future<Either<Failure, Void>> delete(Integer id) {
        taskList.removeIf(task -> task.getId() == id);
        return CompletableFuture.completedFuture(Either.right(null));
    }

    @Override
    public Future<Either<Failure, Task>> get(Integer id) {
        var task =taskList.stream().filter(taskId -> taskId.getId() == id).toList().get(0);
        return CompletableFuture.completedFuture(Either.right(task));
    }

    @Override
    public Future<Either<Failure, Iterable<Task>>> getAll() {
        return CompletableFuture.completedFuture(Either.right(taskList));
    }
}
