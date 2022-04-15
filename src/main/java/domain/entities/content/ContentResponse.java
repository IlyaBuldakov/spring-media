package domain.entities.content;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * @author IlyaBuldakov
 */

@AllArgsConstructor
public class ContentResponse {

    private @Getter List<Content> contents;

    private @Getter int total;
}
