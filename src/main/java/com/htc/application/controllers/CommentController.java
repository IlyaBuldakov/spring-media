package com.htc.application.controllers;

import com.htc.application.dto.comment.CommentRequestDto;
import com.htc.domain.usecases.comment.CreateComment;
import com.htc.utility.Controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с комментариями.
 */
@RestController
@RequestMapping(path = "api/comments")
@AllArgsConstructor
@SecurityRequirement(name = "JWT")
@Tags(@Tag(name = "Комментарии"))
public class CommentController {
  private CreateComment createComment;

  /**
   * Создаёт комментарий.
   */
  @PostMapping
  @Async
  @Operation(summary = "Добавить новый комментарий в задачу")
  public void create(@RequestBody CommentRequestDto commentRequestDto) {
    Controllers.handleRequest(
            createComment,
            new CreateComment.Params(
                    commentRequestDto.user(),
                    commentRequestDto.task(),
                    commentRequestDto.message()),
            null);
  }
}
