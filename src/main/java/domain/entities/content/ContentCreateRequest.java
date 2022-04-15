package domain.entities.content;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;

/**
 * @author IlyaBuldakov
 */

@AllArgsConstructor
public class ContentCreateRequest {

    private @Getter File file;

    private @Getter int task;
}
