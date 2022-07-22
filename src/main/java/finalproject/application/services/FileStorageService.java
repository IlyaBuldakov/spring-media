package finalproject.application.services;


import java.nio.file.Path;
import org.springframework.web.multipart.MultipartFile;

/**
 * Интерфейс для работы с файлами, общий для сохранения Файлов и Контента.
 */
public interface FileStorageService {

  public boolean save(MultipartFile file, Path path, String filename);



}

