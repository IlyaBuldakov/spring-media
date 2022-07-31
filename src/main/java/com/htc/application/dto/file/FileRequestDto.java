package com.htc.application.dto.file;

import com.htc.domain.entities.attributes.Id;
import org.springframework.web.multipart.MultipartFile;

/**
 * Представление запроса создания файла.
 *
 * @param file Файл задачи.
 * @param taskId Задача связанная с создаваемым файлом.
 */
public record FileRequestDto(MultipartFile file, Id taskId) {
}