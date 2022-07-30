package com.htc.application.dto.comment;

import com.htc.application.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Представление комментария (запрос).
 */
@AllArgsConstructor
public class CommentRequest implements BaseResponse {

  private @Getter String taskId;

  private @Getter String message;
}
