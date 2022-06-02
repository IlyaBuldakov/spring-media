package finalproject.domain.entities.content;



import lombok.Getter;

public enum ContentStatus {
  NOT_PUBLISHED(0, "Not published"),
  PUBLISHED(1, "Published");
  final @Getter int id;
  final @Getter String name;
  ContentStatus(int id, String name) {
    this.id = id;
    this.name = name;
  }
}

