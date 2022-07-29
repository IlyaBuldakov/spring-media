package com.htc.domain.util.impl;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.File;
import com.htc.domain.util.FileMetadata;
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
