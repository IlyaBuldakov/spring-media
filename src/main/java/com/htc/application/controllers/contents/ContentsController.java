package com.htc.application.controllers.contents;

import com.htc.application.dto.content.ContentResponse;
import com.htc.application.services.ContentsService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@RequestMapping("/api/contents")
public class ContentsController {

    ContentsService contentsService;

    @GetMapping
    @Async
    public CompletableFuture<List<ContentResponse>> getAll() {
        return contentsService.getAll();
    }

    @PostMapping
    @Async
    public CompletableFuture<Void> create(@RequestParam("file") MultipartFile file,
                                          @RequestParam("task") String taskId) {
        return contentsService.create(file, taskId);
    }

    @DeleteMapping("/{id}")
    @Async
    public CompletableFuture<Void> delete(@PathVariable("id") String id) {
        return contentsService.delete(id);
    }
}
