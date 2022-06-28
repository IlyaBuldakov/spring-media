package ru.kiryanovid.infrastructure.repositories;

import com.github.javafaker.Faker;
import io.vavr.control.Either;
import org.springframework.stereotype.Component;
import ru.kiryanovid.domain.entity.content.Content;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.entity.errors.InvalidValue;
import ru.kiryanovid.domain.entity.task.ContentType;
import ru.kiryanovid.domain.entity.task.Status;
import ru.kiryanovid.domain.entity.task.Task;
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
    private static FakeUserRepositories fur = new FakeUserRepositories();
    private static final List<Task> taskList = List.of(
            Task.create(
                    1,
                    faker.lorem().characters(50),
                    ContentType.AUDIO,
                    faker.lorem().characters(50),
                    Path.of(faker.file().fileName()),
                    fur.randomGet(),
                    fur.randomGet(),
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
                    fur.randomGet(),
                    fur.randomGet(),
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
                    fur.randomGet(),
                    fur.randomGet(),
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(1),
                    new Content(),
                    List.of(),
                    Status.IN_WORK
            ).get()
    );

   // public FakeTaskRepositories(FakeUserRepositories fur) {
   //     this.fur = fur;
   // }

    @Override
    public CompletableFuture<Either<Failure, Task>> create(Task task) {
        taskList.add(task);
        return CompletableFuture.completedFuture(Either.right(null));
    }

    @Override
    public Future<Either<Failure, Task>> update(Task task) {
        var index = taskList.indexOf(task);
        taskList.set(index, task);
        return null;
    }

    @Override
    public Future<Either<Failure, Void>> delete(Integer id) {
        taskList.removeIf(task -> task.getId() == id);
        return CompletableFuture.completedFuture(Either.right(null));
    }

    @Override
    public CompletableFuture<Either<Failure, Task>> get(Integer id) {
        var result =taskList.stream().filter(taskId -> taskId.getId() == id).toList();
        if(result.size() == 0){
            return CompletableFuture.completedFuture(Either.left(InvalidValue.DEFAULT_MESSAGE));
        }

        return CompletableFuture.completedFuture(Either.right(result.get(0)));
    }

    @Override
    public CompletableFuture<Either<Failure, Iterable<Task>>> getAll() {
        return CompletableFuture.completedFuture(Either.right(taskList));
    }
}
