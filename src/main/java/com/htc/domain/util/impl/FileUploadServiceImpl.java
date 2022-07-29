package com.htc.domain.util.impl;

import com.htc.domain.util.FileUploadService;
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

  /**
   * Загружает файл в каталог.
   *
   * @param file Файл.
   */
  public void uploadFile(MultipartFile file) {
    try {
      String uploadDir = "src/main/webapp";
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
