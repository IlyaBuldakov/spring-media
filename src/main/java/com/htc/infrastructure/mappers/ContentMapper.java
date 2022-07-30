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

    public ContentMapper(String name, ContentType type, int authorId, ContentFormat format, String url, int taskId ) {
        this.type = type;
        this.name = name;
        this.dateCreated = LocalDate.now();
        this.authorId = authorId;
        this.format = format;
        this.url = url;
        this.taskId = taskId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public @Getter Integer id;

    @Enumerated(EnumType.STRING)
    private @Getter ContentType type;

    private @Getter String name;

    private @Getter LocalDate dateCreated;

    @ManyToOne
    @JoinColumn(name = "author", insertable = false, updatable = false)
    private @Getter UserMapper author;

    @Column(name = "author")
    private @Getter int authorId;

    @Enumerated(EnumType.STRING)
    private @Getter ContentFormat format;

    private @Getter String url;

    private @Getter String preview;

    @Column(name = "task_id")
    private @Getter Integer taskId;
}
