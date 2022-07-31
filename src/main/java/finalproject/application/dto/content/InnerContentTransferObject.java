package finalproject.application.dto.content;

import finalproject.domain.entities.content.ContentFormat;
import finalproject.domain.entities.content.ContentType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InnerContentTransferObject {
  String filename;
  ContentType type;
  ContentFormat format;
  String extension;

}