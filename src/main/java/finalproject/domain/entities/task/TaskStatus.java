package finalproject.domain.entities.task;

import lombok.Getter;

/**
 * Статусы задач.
 */
public enum TaskStatus {
  INWORK(0, "In work"),
  FEEDBACK(1, "Feedback"),
  APPROVED(2, "Approved");
  final @Getter int id;
  final @Getter String name;

  TaskStatus(int id, String name) {
    this.id = id;
    this.name = name;
  }
}

