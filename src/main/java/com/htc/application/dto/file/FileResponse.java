package com.htc.application.dto.file;

import com.htc.domain.entities.file.File;
import com.htc.domain.entities.file.File.FileFormat;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Представление файла (ответ).
 */
public class FileResponse {

    /**
     * Конструктор из соответствующей сущности.
     *
     * @param file Сущность {@link File файла}.
     */
    public FileResponse(File file) {
        this.id = file.getId();
        this.name = file.getName();
        this.dateCreated = file.getDateCreated();
        this.fileFormat = file.getFormat();
        this.url = file.getUrl();
    }

    /**
     * Создание списка представления файлов
     * из списка соответствующих сущностей.
     *
     * @param list Список сущностей {@link File}.
     * @return Список представлений (DTO) {@link FileResponse}.
     */
    public static List<FileResponse> createFromEntityList(List<? extends File> list) {
        List<FileResponse> resultList = new ArrayList<>();
        for (File file : list) {
            resultList.add(new FileResponse(file));
        }
        return resultList;
    }

    /**
     * Идентификатор файла.
     */
    private final @Getter int id;

    /**
     * Имя файла.
     */
    private final @Getter String name;

    /**
     * Дата создания файла.
     */
    private final @Getter LocalDate dateCreated;

    /**
     * Формат файла.
     */
    private final @Getter FileFormat fileFormat;

    /**
     * URL файла на сервере.
     */
    private final @Getter String url;
}
