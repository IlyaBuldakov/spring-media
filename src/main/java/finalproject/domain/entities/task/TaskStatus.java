package finalproject.domain.entities.task;

import lombok.Getter;
import java.util.HashMap;

/**
 * Статусы задач.
 */
public enum TaskStatus {
  INWORK(0, "inWork"),
  FEEDBACK(1, "feedback"),
  APPROVED(2, "approved");
  final @Getter int id;
  final @Getter String name;

  TaskStatus(int id, String name) {
    this.id = id;
    this.name = name;
  }

  static final HashMap<String, TaskStatus> map = new HashMap<>();

  static {
    for (TaskStatus taskStatus : TaskStatus.values()) {
      map.put(taskStatus.getName(), taskStatus);
    }
  }

  public static TaskStatus getTaskStatusByName(String name) {
    return map.get(name);
  }

}

