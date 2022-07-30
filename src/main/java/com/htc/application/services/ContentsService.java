package com.htc.application.services;

import com.htc.application.dto.content.ContentResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Интерфейс, описывающий базовые операции для взаимодействия с контентом.
 * Слой преобразования DTO <---> Domain entity.
 */
public interface ContentsService {

    /**
     * Получение списка контента.
     *
     * @return Список контента.
     */
    CompletableFuture<List<ContentResponse>> getAll();

    /**
     * Создание контента.
     *
     * @param file Файл контента.
     * @param taskId Идентификатор задачи.
     * @return void.
     */
    CompletableFuture<Void> uploadContent(int authorId, MultipartFile file, String taskId);

    /**
     * Удаление контента по идентификатору.
     *
     * @param id Идентификатор контента.
     * @return void.
     */
    CompletableFuture<Void> delete(String id);
}
