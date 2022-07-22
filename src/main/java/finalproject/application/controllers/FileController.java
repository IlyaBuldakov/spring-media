package finalproject.application.controllers;


import finalproject.application.services.ContentService;
import finalproject.application.services.FileStorageService;
import finalproject.application.services.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import java.util.concurrent.ExecutionException;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Контроллер для загрузки контента.
 */
@AllArgsConstructor
@RestController
public class FileController {

  FileStorageService fileStorageService;
  TaskService taskService;
  ContentService contentService;



  @ApiOperation(value = "", authorizations = { @Authorization(value = "Bearer") })
  @PostMapping(value = "/api/contents", consumes = {"multipart/form-data"})
  public String uploadContent(@RequestPart("file") MultipartFile file,
                              @RequestParam("task") int taskId,
                              HttpServletRequest request)
          throws ExecutionException, InterruptedException {
    return contentService.attachFileToTask(file, taskId).get().get();

  }



}