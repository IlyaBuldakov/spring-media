package domain.entities.files;

import java.time.LocalDateTime;

public class FileDto {

    private int id;
    private String name;
    private LocalDateTime dateCreated;
    private FileFormat format;
    private String url;
}
