package com.htc.application.services.impl;

import com.htc.application.dto.content.ContentResponse;
import com.htc.application.services.ContentsService;
import com.htc.application.services.ExceptionDtoResolver;
import com.htc.domain.entities.content.Content.Format;
import com.htc.domain.entities.content.ContentType;
import com.htc.domain.usecases.contents.CreateContent;
import com.htc.domain.usecases.contents.DeleteContentById;
import com.htc.domain.usecases.contents.GetAllContent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Реализация {@link ContentsService}.
 */
@Service
@AllArgsConstructor
public class ContentsServiceImpl implements ContentsService {

    GetAllContent getAllContent;
    CreateContent createContent;
    DeleteContentById deleteContentById;

    /**
     * Получение списка всего контента.
     *
     * @return Список контента.
     */
    public CompletableFuture<List<ContentResponse>> getAll() {
        return getAllContent.execute()
                .thenApply(either -> either.map(
                        list -> list.parallelStream()
                                .map(ContentResponse::new)
                                .toList()
                ).getOrElseThrow(ExceptionDtoResolver::resolve));
    }

    /**
     * Создание контента.
     *
     * @param file Файл.
     * @param taskId Идентификатор задачи.
     * @return void.
     */
    @Override
    public CompletableFuture<Void> create(MultipartFile file, String taskId) {
        return createContent.execute(file.getOriginalFilename(), ContentType.PHOTO, Format.AVI, taskId)
                .thenApply(either -> {
                    if (either.isLeft()) {
                        throw ExceptionDtoResolver.resolve(either.getLeft());
                    }
                    return null;
                });
    }

    /**
     * Удаление контента по идентификатору.
     *
     * @param id Идентификатор контента.
     * @return void.
     */
    public CompletableFuture<Void> delete(String id) {
        return deleteContentById.execute(id)
                .thenApply(either -> {
                    if (either.isLeft()) {
                        throw ExceptionDtoResolver.resolve(either.getLeft());
                    }
                    return null;
                });
    }
}