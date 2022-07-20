package com.htc.application.dto.task;

import com.htc.domain.entities.content.ContentType;
import lombok.Getter;

import java.time.LocalDate;

public class TaskRequest {

    private @Getter String name;

    private @Getter ContentType type;

    private @Getter String description;

    private @Getter String author;

    private @Getter String executor;

    private @Getter LocalDate dateExpired;
}
