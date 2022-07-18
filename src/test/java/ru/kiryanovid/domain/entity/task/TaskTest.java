package ru.kiryanovid.domain.entity.task;

import com.github.javafaker.Faker;
import ru.kiryanovid.domain.entity.comment.Comment;
import ru.kiryanovid.domain.entity.content.Content;
import ru.kiryanovid.domain.entity.file.File;
import ru.kiryanovid.domain.entity.users.User;
import org.junit.jupiter.api.Test;
import ru.kiryanovid.domain.entity.users.Role;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
/*
class TaskTest {
    File file = new File();
    Content content = new Content();
    Iterable<Comment> comment = List.of();
    private static final Faker faker = Faker.instance(new Locale("ru"));

    @Test
    void create_ValidValues_ShouldCreateTask() {
        var task = Task.create(1,
                "Task1",
                ContentType.PHOTO,
                "Description1",
                file,
                User.create(
                        1,
                        faker.name().fullName(),
                        faker.internet().emailAddress(),
                        faker.internet().password(5, 17) + "1aA",
                        faker.lorem().characters(40),
                        Role.ADMIN
                ).get(),
                null,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1L),
                content,
                comment,
                Status.APPROVED);
        assertThat(task).isInstanceOf(Task.class);
    }
   
}*/