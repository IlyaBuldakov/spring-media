package finalproject.application.services;


import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Path;


/**
 * Интерфейс для работы с файлами, общий для сохранения Файлов и Контента.
 */
public interface FileStorageService {

  boolean save(MultipartFile file, Path path, String filename);



}

