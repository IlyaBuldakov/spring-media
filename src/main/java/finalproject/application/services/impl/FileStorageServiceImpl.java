package finalproject.application.services.impl;


import finalproject.application.services.FileStorageService;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


/**
 * Сервис для хранения Файлов и Контента.
 */
@Service
public class FileStorageServiceImpl implements FileStorageService {


  @Override
  public boolean save(MultipartFile file, Path path, String filename) {

    if (file.isEmpty() || filename.contains("..")) {
      return false;
    }


    try {
      if (!Files.exists(path)) {
        Files.createDirectory(path);
      }
      Files.copy(file.getInputStream(), path.resolve(filename),
              StandardCopyOption.REPLACE_EXISTING);
      return true;
    } catch (Exception e) {
      return false;
    }


  }
}
