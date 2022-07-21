package com.htc.application.controllers;

import com.htc.application.dto.comment.CommentRequestDto;
import com.htc.domain.usecases.comment.CreateComment;
import com.htc.utility.Controllers;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с пользователями.
 */
@RestController
@RequestMapping(path = "api/comments")
@AllArgsConstructor
public class CommentController {
  private CreateComment createComment;

  /**
   * Создаёт пользователя.
   */
  @PostMapping
  @Async
  public void create(@RequestBody CommentRequestDto commentRequestDto) {
    Controllers.handleRequest(
            createComment,
            new CreateComment.Params(
                    commentRequestDto.getUser(),
                    commentRequestDto.getTask(),
                    commentRequestDto.getMessage()),
            null);
  }
}
