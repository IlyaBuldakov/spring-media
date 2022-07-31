package com.htc.infrastructure.repositories;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.Task;
import com.htc.domain.entities.User;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.repositories.ContentRepository;
import com.htc.infrastructure.models.ContentModel;
import com.htc.infrastructure.models.TaskModel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория медиаконтента.
 */
@Repository
@AllArgsConstructor
public class ContentRepositoryImpl implements ContentRepository {

  Contents contents;

  TaskRepositoryImpl taskRepository;

  @Override
  public Content create(
      Task task,
      Content.Type type,
      Content.Name name,
      LocalDateTime dateCreated,
      User author,
      Content.Format format,
      Content.Url url,
      Content.Url preview) {
    return null;
  }

  @Override
  public void delete(Id id) {
    contents.deleteById(id.getValue());
  }

  @Override
  public Collection<Content> getFeedByQuery(
      Integer page,
      Integer count,
      String author,
      LocalDate date,
      Integer typeId) {
    Content.Type type = Content.Type.values()[typeId];
    var contentFeed =
        contents
            .findAllByAuthorNameAndDateCreatedAndType(author, date, type)
            .stream()
            .map(ContentModel::toEntity)
            .collect(Collectors.toCollection(ArrayList::new));
    int firstIndex = page * count;
    int lastIndex = firstIndex + count;
    return contentFeed.subList(firstIndex, lastIndex);

  }


  @Override
  public Collection<Content> findByTask(Task task) {
    var taskModel = taskRepository.tasks.findById(task.id().getValue()).get();

    return contents
        .findAllByParentTask(taskModel)
        .stream()
        .map(ContentModel::toEntity)
        .collect(Collectors.toList());
  }

  @Override
  public boolean exists(Id id) {
    return contents.existsById(id.getValue());
  }


  /**
   * ORM для доступа к данным медиаконтента в СУБД.
   */
  public interface Contents extends JpaRepository<ContentModel, Integer> {
    List<ContentModel> findAllByParentTask(TaskModel taskModel);

    //TODO: Запрос должен сдержать строку - часть имени автора, а не его идентификатор.
    List<ContentModel> findAllByAuthorNameAndDateCreatedAndType(String author,
                                                                LocalDate date,
                                                                Content.Type type);

  }
}
