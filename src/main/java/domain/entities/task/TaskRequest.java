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
public class TaskRequest {

    private @Getter String name;

    private @Getter ContentType type;

    private @Getter String description;

    private @Getter UserBasic author;

    private @Getter UserBasic executor;

    private @Getter LocalDate dateExpired;
}
