package com.htc.application.dto.content;

import com.htc.application.dto.user.UserShortResponse;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.Content.Format;
import com.htc.domain.entities.content.ContentType;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContentResponse {

    public ContentResponse(Content content) {
        this.id = content.getId();
        this.type = content.getType();
        this.dateCreated = content.getDateCreated();
        this.author = new UserShortResponse(content.getAuthor());
        this.format = content.getFormat();
        this.url = content.getUrl();
        this.preview = content.getPreview();
    }

    public static List<ContentResponse> createFromEntityList(List<? extends Content> list) {
        List<ContentResponse> resultList = new ArrayList<>();
        for (Content content : list) {
            resultList.add(new ContentResponse(content));
        }
        return resultList;
    }

    private final @Getter int id;

    private final @Getter ContentType type;

    private final @Getter LocalDate dateCreated;

    private final @Getter UserShortResponse author;

    private final @Getter Format format;

    private final @Getter String url;

    private final @Getter String preview;
}
