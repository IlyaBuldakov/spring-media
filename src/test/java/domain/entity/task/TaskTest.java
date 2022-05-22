package domain.entity.task;

import domain.entity.Comment;
import domain.entity.Content;
import domain.entity.User;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class TaskTest {
    Path testPath = Path.of("c:/test.png");
    User user = new User();
    Content content = new Content();
    Comment comment = new Comment();

    @Test
    void create_ValidValues_ShouldCreateTask() {
        var task = new Task(1,
                "Task1",
                ContentType.PHOTO,
                "Description1",
                testPath,
                user,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1L),
                content,
                comment,
                Status.APPROVED);
        assertThat(task).isInstanceOf(Task.class);
    }
   
}