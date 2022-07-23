package com.htc.application.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Сервис для загрузки файлов.
 */
@Service
public interface FileUploadService {

  void uploadFile(MultipartFile file);
}
