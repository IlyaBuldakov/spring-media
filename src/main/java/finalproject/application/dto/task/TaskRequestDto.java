package finalproject.application.dto.task;

import finalproject.application.dto.content.ContentTypeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * Запрос создания / изменения задачи.
 */
@AllArgsConstructor
public class TaskRequestDto {


  private @Getter String name;

  private @Getter String type;

  private @Getter String description;

  private @Getter String taskStatus;

  private @Getter int authorId;

  private @Getter int contentMakerId;

  private @Getter String dateExpired;

}
