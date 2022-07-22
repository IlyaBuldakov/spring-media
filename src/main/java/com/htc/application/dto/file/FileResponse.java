package com.htc.application.dto.file;

import com.htc.domain.entities.file.File;
import com.htc.domain.entities.file.File.Format;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileResponse {

    public FileResponse(File file) {
        this.id = file.getId();
        this.name = file.getName();
        this.dateCreated = file.getDateCreated();
        this.format = file.getFormat();
        this.url = file.getUrl();
    }
    public static List<FileResponse> createFromEntityList(List<? extends File> list) {
        List<FileResponse> resultList = new ArrayList<>();
        for (File file : list) {
            resultList.add(new FileResponse(file));
        }
        return resultList;
    }


    private final @Getter int id;

    private final @Getter String name;

    private final @Getter LocalDate dateCreated;

    private final @Getter Format format;

    private final @Getter String url;
}
