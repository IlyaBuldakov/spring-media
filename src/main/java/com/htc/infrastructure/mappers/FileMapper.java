package com.htc.infrastructure.mappers;

import com.htc.domain.entities.file.File;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "files")
@NoArgsConstructor
public class FileMapper implements File {

    public FileMapper(String name, LocalDate dateCreated, Format format, String url, int taskId) {
        this.name = name;
        this.dateCreated = dateCreated;
        this.format = format;
        this.url = url;
        this.taskId = taskId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public @Getter Integer id;

    private @Getter String name;

    private @Getter LocalDate dateCreated;

    @Enumerated(EnumType.STRING)
    private @Getter Format format;

    private @Getter String url;

    @Column(name = "task_id")
    private @Getter Integer taskId;
}
