package com.htc.domain.util.impl;

import com.htc.domain.entities.Content;
import com.htc.domain.util.PreviewPicture;
import java.io.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Реализация интерфейса для создания миниатюры медиаконтента.
 */
@Service
public class PreviewPictureImpl implements PreviewPicture {
  @Override
  public File createPreview(MultipartFile file, Content.Type type) {
    return switch (type) {
      case VIDEO -> createVideoPreview(file);
      case PHOTO -> createPhotoPreview(file);
      default -> createAudioPreview(file);
    };
  }

  @Override
  public File createVideoPreview(MultipartFile file) {
    return null;
  }

  @Override
  public File createPhotoPreview(MultipartFile file) {
    return null;
  }

  @Override
  public File createAudioPreview(MultipartFile file) {
    return null;
  }
}
