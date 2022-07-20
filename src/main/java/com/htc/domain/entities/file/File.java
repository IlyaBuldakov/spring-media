package com.htc.domain.entities.file;

import java.time.LocalDate;

public interface File {

    enum Format {

        DOC,

        DOCX,

        XLS,

        XLSX,

        PDF
    }

    Integer getId();

    String getName();

    LocalDate getDateCreated();

    Format getFormat();

    String getUrl();
}
