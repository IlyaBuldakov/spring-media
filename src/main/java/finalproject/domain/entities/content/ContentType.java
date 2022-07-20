package finalproject.domain.entities.content;


import lombok.Getter;

import java.util.HashMap;

public enum ContentType {
  VIDEO(0, "video"),
  AUDIO(1, "audio"),
  PHOTO(2, "photo");
  final @Getter int id;
  final @Getter String name;

  ContentType(int id, String name) {
    this.id = id;
    this.name = name;
  }
  static final HashMap<String, ContentType> map = new HashMap<>();
  static {
    for (ContentType contentType : ContentType.values()) {
      map.put(contentType.getName(), contentType);
    }
  }
  public static ContentType getContentTypeByName(String name) {
    return map.get(name);
  }
}
