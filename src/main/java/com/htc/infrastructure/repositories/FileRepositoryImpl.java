package com.htc.infrastructure.repositories;

import com.htc.domain.entities.File;
import com.htc.domain.entities.Task;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.repositories.FileRepository;
import com.htc.infrastructure.models.FileModel;
import com.htc.infrastructure.models.TaskModel;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория файлов.
 */
@Repository
@AllArgsConstructor
public class FileRepositoryImpl implements FileRepository {

  Files files;
  TaskRepositoryImpl taskRepository;

  @Override
  public File create(
      File.Name name,
      LocalDateTime dateCreated,
      File.Format format,
      File.Url url,
      Task task) {

    var taskModel = taskRepository.tasks.findById(task.id().getValue());
    var file = new FileModel(
        name.getValue(),
        dateCreated,
        format,
        url.getValue(),
        taskModel.get());
    files.save(file);
    return file.toEntity();
  }

  @Override
  public void delete(Id id) {
    files.deleteById(id.getValue());
  }

  @Override
  public Optional<File> get(Id id) {
    var file = files.findById(id.getValue());
    return files
        .findById(id.getValue())
        .map(FileModel::toEntity);
  }

  @Override
  public Collection<File> getAllByTask(Task task) {
    var taskModel = taskRepository.tasks.findById(task.id().getValue()).get();

    return files
        .findAllByTask(taskModel)
        .stream()
        .map(FileModel::toEntity)
        .collect(Collectors.toList());
  }

  @Override
  public boolean exists(Id id) {
    return files.existsById(id.getValue());
  }

  /**
   * ORM для доступа к данным пользователей в СУБД.
   */
  interface Files extends JpaRepository<FileModel, Integer> {
    List<FileModel> findAllByTask(TaskModel taskModel);
  }
}
