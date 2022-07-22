package com.htc.domain.entities.file;

import java.time.LocalDate;

/**
 * Интерфейс, описывающий сущность файла.
 */
public interface File {

    /**
     * Формат файла.
     */
    enum Format {

        DOC,

        DOCX,

        XLS,

        XLSX,

        PDF
    }

    /**
     * Идентификатор файла.
     *
     * @return Идентификатор файла.
     */
    Integer getId();

    /**
     * Имя файла.
     *
     * @return Имя файла.
     */
    String getName();

    /**
     * Дата создания файла.
     *
     * @return Дата создания файла.
     */
    LocalDate getDateCreated();

    /**
     * Формат файла.
     *
     * @return Формат файла.
     */
    Format getFormat();

    /**
     * URL файла.
     *
     * @return URL файла.
     */
    String getUrl();
}
