package com.htc.infrastructure.mappers;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.ContentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "contents")
@AllArgsConstructor
@NoArgsConstructor
public class ContentMapper implements Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public @Getter Integer id;

    @Enumerated(EnumType.STRING)
    private @Getter ContentType type;

    private @Getter String name;

    private @Getter LocalDate dateCreated;

    @ManyToOne
    @JoinColumn(name = "author",insertable = false, updatable = false)
    private @Getter UserMapper author;

    @Enumerated(EnumType.STRING)
    private @Getter Format format;

    private @Getter String url;

    private @Getter String preview;

    @Column(name = "task_id")
    private @Getter Integer taskId;
}
