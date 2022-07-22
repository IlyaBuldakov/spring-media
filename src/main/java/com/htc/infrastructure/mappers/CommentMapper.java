package com.htc.infrastructure.mappers;

import com.htc.domain.entities.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
public class CommentMapper implements Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public @Getter Integer id;

    private @Getter LocalDate dateCreated;

    @OneToOne(fetch = FetchType.EAGER)
    private @Getter UserMapper author;

    private @Getter String message;
}
