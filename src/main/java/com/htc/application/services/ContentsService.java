package com.htc.application.services;

import com.htc.application.dto.content.ContentResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ContentsService {

    CompletableFuture<List<ContentResponse>> getAll();

    CompletableFuture<Void> create(MultipartFile file, String taskId);

    CompletableFuture<Void> delete(String id);
}
