package domain.entities.task;

import domain.entities.content.ContentType;
import domain.entities.user.UserBasic;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

/**
 * @author IlyaBuldakov
 */

@AllArgsConstructor
public class TaskList {

    private @Getter int id;

    private @Getter String name;

    private @Getter ContentType type;

    private @Getter UserBasic executor;

    private @Getter LocalDate dateExpired;

    private @Getter TaskStatus status;
}
