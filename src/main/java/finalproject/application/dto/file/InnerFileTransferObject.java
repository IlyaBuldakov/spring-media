package finalproject.application.dto.file;


import finalproject.domain.entities.filedocuments.Format;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InnerFileTransferObject {
  String filename;
  Format format;

}

