package com.htc.application.controllers;

import com.htc.application.dto.responsestatus.NotFoundResponse;
import com.htc.domain.entities.failures.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorContoller {
  @GetMapping("/error")
  public ResponseEntity<NotFoundResponse> handleError() {
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
