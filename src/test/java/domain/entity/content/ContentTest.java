package domain.entity.content;


import domain.entity.User;
import domain.entity.task.ContentType;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ContentTest {
    @Test
    void create_ValidValues_ShouldCreateContent(){
        Path pathAudio = Path.of("c:/file.flac");
        Path pathPreview = Path.of("c:/preview.jpg");
        var content = new Content(1,
                ContentType.AUDIO,
                "content",
                LocalDateTime.now(),
                new User(),
                ContentFormat.FLAC,
                pathAudio,
                pathPreview);

        assertThat(content).isInstanceOf(Content.class);
    }

}