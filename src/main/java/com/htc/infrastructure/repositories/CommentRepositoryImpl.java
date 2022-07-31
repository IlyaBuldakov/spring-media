package com.htc.infrastructure.repositories;

import com.htc.domain.entities.Comment;
import com.htc.domain.entities.Task;
import com.htc.domain.entities.User;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.repositories.CommentRepository;
import com.htc.infrastructure.models.CommentModel;
import com.htc.infrastructure.models.FileModel;
import com.htc.infrastructure.models.TaskModel;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория комментария.
 */
@Repository
@AllArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

  Comments comments;
  TaskRepositoryImpl taskRepository;
  UserRepositoryImpl userRepository;

  @Override
  public Comment create(
      User user,
      Task task,
      LocalDateTime dateCreated,
      Comment.Message message) {
    var userModel = userRepository.users.findById(user.id().getValue());
    var taskModel = taskRepository.tasks.findById(task.id().getValue());

    var comment = new CommentModel(
        dateCreated,
        userModel.get(),
        taskModel.get(),
        message.getValue());

    comment = comments.save(comment);
    return comment.toEntity();
  }

  @Override
  public Collection<Comment> getAllByTask(Task task) {
    var taskModel = taskRepository.tasks.findById(task.id().getValue()).get();

    return comments
        .findAllByTask(taskModel)
        .stream()
        .map(CommentModel::toEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Id id) {
    comments.deleteById(id.getValue());
  }

  @Override
  public boolean exists(Id id) {
    return comments.existsById(id.getValue());
  }

  /**
   * ORM для доступа к данным пользователей в СУБД.
   */
  public interface Comments extends JpaRepository<CommentModel, Integer> {

    List<CommentModel> findAllByTask(TaskModel taskModel);
  }
}
