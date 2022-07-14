package finalproject.application.services.impl;

import finalproject.application.services.FileStorageService;
import finalproject.configuration.StorageProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class FileStorageServiceImpl implements FileStorageService {



  @Override
  public void save(MultipartFile file, Path path) {

      try {
        Files.copy(file.getInputStream(),path.resolve(file.getOriginalFilename()));
      } catch (IOException e) {
        throw new RuntimeException("Could not store the file. Error:"+e.getMessage());
      }
    }


}
