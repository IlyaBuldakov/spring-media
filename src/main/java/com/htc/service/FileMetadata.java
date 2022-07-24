package com.htc.service;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.files.File;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Сервис для определения типа и формата файла.
 */
@Service
public interface FileMetadata {

  Optional<Content.Format> getContentFormat(MultipartFile file);

  Optional<Content.Type> getContentType(MultipartFile file);

  Optional<File.Format> getFileFormat(MultipartFile file);
}
