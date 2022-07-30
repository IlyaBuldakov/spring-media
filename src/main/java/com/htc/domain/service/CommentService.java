package com.htc.domain.service;

import com.htc.domain.entities.Comment;
import com.htc.domain.entities.Task;
import com.htc.domain.entities.User;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.usecases.CommentUseCase;
import io.vavr.control.Either;

public class CommentService {
  private CommentUseCase.Create create;

  /**
   * Создаёт комментарий.
   *
   * @param subjectId Идентификатор пользователя, выполняющего данную операцию.
   * @param user Пользователь - автор комментария.
   * @param task Задача под которой остален комментарий.
   * @param message Тест комментария.
   * @return Ошибка или комментарий.
   */
  public Either<Failure, Comment> create(
          Id subjectId,
          User user,
          Task task,
          Comment.Message message
  ) {
    return this.create.execute(
            subjectId,
            new CommentUseCase.Create.Params(user, task, message)
    );
  }
}
