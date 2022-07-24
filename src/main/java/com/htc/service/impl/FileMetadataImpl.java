package com.htc.service.impl;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.files.File;
import com.htc.service.FileMetadata;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Реализация сервиса для определения типа и формата файла.
 */
@Service
public class FileMetadataImpl implements FileMetadata {
  @Override
  public Optional<Content.Format> getContentFormat(MultipartFile file) {
    return Optional.empty();
  }

  @Override
  public Optional<Content.Type> getContentType(MultipartFile file) {
    return Optional.empty();
  }

  @Override
  public Optional<File.Format> getFileFormat(MultipartFile file) {
    return Optional.empty();
  }
}
