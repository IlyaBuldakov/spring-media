package finalproject.domain.entities.filedocuments;

/**
 * Форматы файлов документов.
 */
public enum Format {
  DOC("application/msword"),
  DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
  XLS("application/vnd.ms-excel"),
  XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
  PDF("application/pdf");

  public String myme;

  Format(String myme) {
    this.myme = myme;
  }

}
