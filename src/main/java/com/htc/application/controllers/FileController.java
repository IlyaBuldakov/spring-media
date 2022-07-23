package com.htc.application.controllers;

import com.htc.application.service.FileUploadService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
@AllArgsConstructor
public class FileController {

  private FileUploadService fileUploadService;

  @PostMapping
  public void uploadFile(@RequestParam("file") MultipartFile file, int taskId) {

    fileUploadService.uploadFile(file);
  }
}