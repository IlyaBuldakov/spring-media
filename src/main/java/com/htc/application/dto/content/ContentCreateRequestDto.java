package com.htc.application.dto.content;

import com.htc.domain.entities.attributes.Id;
import org.springframework.web.multipart.MultipartFile;

/**
 * Представление запроса создания медиаконтента.
 *
 * @param file Файл медиаконтента.
 * @param taskId Задача связаная с создаваемым медиаконтента.
 */
public record ContentCreateRequestDto(MultipartFile file, Id taskId) {
}
