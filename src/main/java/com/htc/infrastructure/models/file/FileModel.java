package com.htc.infrastructure.models.file;

import com.htc.domain.entities.file.File;
import com.htc.domain.entities.file.Format;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.entities.utility.parameters.file.FileBody;
import com.htc.domain.entities.utility.parameters.file.FileName;
import com.htc.domain.entities.utility.parameters.file.FileUrlPath;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import lombok.AllArgsConstructor;

/**
 * Представление сущности файла в СУБД.
 */
@Entity
@Table(name = "FILES")
@AllArgsConstructor
public class FileModel implements File {
  @javax.persistence.Id
  @GeneratedValue
  @Column(name = "id", unique = true, nullable = false)
  Long fileId;

  @Column(name = "name", nullable = false)
  String name;

  @Column(name = "dateCreated", nullable = false)
  String dateCreated;

  @Column(name = "format", nullable = false)
  String format;

  @Column(name = "fileurl", nullable = false)
  String fileUrl;

  @Column(name = "file")
  String file;

  public Id getId() {
    return Id.create(this.fileId).get();
  }

  @Override
  public FileName getName() {
    return FileName.create(this.name).get();
  }

  @Override
  public DateCreated getDateCreated() {
    return DateCreated.create(this.dateCreated).get();
  }

  @Override
  public Format getFormat() {
    return Format.getFromName(this.format);
  }

  @Override
  public FileUrlPath getFileUrlPath() {
    return FileUrlPath.create(this.fileUrl).get();
  }

  @Override
  public FileBody getFile() {
    return FileBody.create(this.file).get();
  }

  protected FileModel() {}
}
