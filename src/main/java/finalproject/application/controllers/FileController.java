package finalproject.application.controllers;


import finalproject.application.services.ContentService;
import finalproject.application.services.FileStorageService;
import finalproject.application.services.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;


@AllArgsConstructor
@RestController
public class FileController {

  FileStorageService fileStorageService;
  TaskService taskService;
  ContentService contentService;



  @ApiOperation(value = "", authorizations = { @Authorization(value="Bearer") })
  @PostMapping(value = "/api/contents", consumes = {"multipart/form-data"})
  public String uploadContent(@RequestPart("file") MultipartFile file, @RequestParam("task") int task_id, HttpServletRequest request) throws ExecutionException, InterruptedException {
   return contentService.attachFileToTask(file, task_id).get().get();

  }

  @ApiOperation(value = "", authorizations = { @Authorization(value="Bearer") })
  @PostMapping("/api/files")
  public String uploadFile(@RequestPart("file") MultipartFile file, @RequestPart("task") int task_id, HttpServletRequest request){
    return null;


  }

}