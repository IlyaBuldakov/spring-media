package domain.entities.content;

import domain.entities.user.UserDto;

import java.time.LocalDateTime;

public class ContentDto {

    private int id;
    private ContentTypeDto type;
    private String name;
    private LocalDateTime dateCreated;
    private UserDto author;
    private ContentFormat format;
    private String url;
    private String preview;
}
