package com.htc.service;

import com.htc.domain.entities.Content;
import java.io.File;
import org.springframework.web.multipart.MultipartFile;

/**
 * Интефейс для создания миниатюры медиаконтента.
 */
public interface PreviewPicture {

  File createPreview(MultipartFile file, Content.Type type);

  File createVideoPreview(MultipartFile file);

  File createPhotoPreview(MultipartFile file);

  File createAudioPreview(MultipartFile file);

}
