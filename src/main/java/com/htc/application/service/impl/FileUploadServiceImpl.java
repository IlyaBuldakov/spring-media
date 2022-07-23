package com.htc.application.service.impl;

import com.htc.application.service.FileUploadService;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * Реализация сервиса для зашрузки файлов.
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {
  public String uploadDir = "src/main/webapp";

  /**
   * Загружает файл в каталог.
   *
   * @param file Файл.
   */
  public void uploadFile(MultipartFile file) {
    try {
      Path copyLocation = Paths
              .get(uploadDir
                      + File.separator
                      + StringUtils.cleanPath(file.getOriginalFilename()));
      Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
