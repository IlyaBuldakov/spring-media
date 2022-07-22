package com.htc.domain.usecases.task;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.FilesRepository;
import com.htc.domain.repositories.TasksRepository;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.concurrent.CompletableFuture;

@Component
@AllArgsConstructor
public class DeleteTask {

    TasksRepository tasksRepository;

    FilesRepository filesRepository;

    public CompletableFuture<Either<Failure, Void>> execute(String id) {
        var expectedFailure = ValuesValidator.validateStringId(id);
        if (expectedFailure != null) {
            return CompletableFuture.completedFuture(Either.left(expectedFailure));
        }
        clearRelevantStaticResources(Integer.parseInt(id));
        tasksRepository.deleteById(Integer.parseInt(id));
        return null;
    }

    private void clearRelevantStaticResources(int id) {
        String pathQualifier = "src/main/webapp/";
        var fileUrls = filesRepository.findRelevantToTaskFiles(id);
        if (!fileUrls.isEmpty()) {
            for (String url : fileUrls) {
                new File(pathQualifier + url).delete();
            }
        }
    }
}
