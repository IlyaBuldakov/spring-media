package com.htc.application.controllers;

import com.htc.domain.entities.comment.Comment;
import com.htc.domain.usecases.comment.CreateComment;
import com.htc.domain.usecases.comment.GetCommentsByTaskId;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с комментариями.
 */
@RestController
@RequestMapping(path = "api/comments")
@AllArgsConstructor
public class CommentController {
  private CreateComment createComment;
  private GetCommentsByTaskId getCommentsByTaskId;

  /**
   * Создаёт комментарий.
   */
  @PostMapping
  public void create() {
    throw new UnsupportedOperationException("Метод не реализован");
  }

  /**
   * Возвращает комментарии по идентификатору задачи.
   *
   * @param taskId Идентификатор задачи.
   * @return Комментарии по идентификатору задачи.
   */
  @GetMapping(path = "/{taskId}")
  public Iterable<Comment> getAll(@PathVariable int taskId)
      throws ExecutionException, InterruptedException {
    return getCommentsByTaskId.execute(taskId)
        .get()
        .get();
  }
}
