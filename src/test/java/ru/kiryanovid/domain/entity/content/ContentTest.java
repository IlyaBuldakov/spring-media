package ru.kiryanovid.domain.entity.content;


import com.github.javafaker.Faker;
import ru.kiryanovid.domain.entity.User;
import ru.kiryanovid.domain.entity.task.ContentType;
import org.junit.jupiter.api.Test;
import ru.kiryanovid.domain.entity.users.Role;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

class ContentTest {
    private static final Faker faker = Faker.instance(new Locale("ru"));
    @Test
    void create_ValidValues_ShouldCreateContent(){
        Path pathAudio = Path.of("c:/file.flac");
        Path pathPreview = Path.of("c:/preview.jpg");
        var content = new Content(1,
                ContentType.AUDIO,
                "content",
                LocalDateTime.now(),
                User.create(
                        1,
                        faker.name().fullName(),
                        faker.internet().emailAddress(),
                        faker.internet().password(5, 17) + "1aA",
                        faker.lorem().characters(40),
                        Role.ADMIN
                ).get(),
                ContentFormat.FLAC,
                pathAudio,
                pathPreview);

        assertThat(content).isInstanceOf(Content.class);
    }

}