package finalproject.application.controllers;

import finalproject.application.services.FileStorageService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Path;
import java.nio.file.Paths;


  @AllArgsConstructor
  @RestController
  public class FileController {

    FileStorageService fileStorageService;


    @ApiOperation(value = "", authorizations = { @Authorization(value="Bearer") })
    @PostMapping("/api/contents")
    public String uploadContent(@RequestParam("file") MultipartFile file, HttpServletRequest request){
      String filename =  StringUtils.cleanPath(file.getOriginalFilename());
      String type = file.getContentType();
      Path path = Paths.get("./src/main/resources/static/content");
      fileStorageService.save(file, path);
      return filename + " " + type;

    }

  }

