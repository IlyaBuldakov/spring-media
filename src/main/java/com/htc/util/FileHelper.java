package com.htc.util;

import com.htc.domain.entities.AbstractApplicationFormat;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValue;
import com.htc.domain.entities.file.File.FileFormat;
import com.htc.domain.entities.content.Content.ContentFormat;
import io.vavr.control.Either;
import org.apache.tika.detect.DefaultDetector;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Вспомогательный утилитный класс для работы с файлами.
 */
public class FileHelper {

    /**
     * Получение случайной строки для
     * уникального имени файла.
     *
     * @return Случайная строка.
     */
    public static String getRandomString() {
        int leftLimit = 97; // 'a'
        int rightLimit = 122; // 'z'
        boolean enableUpperCase; // Флаг большого символа
        int targetStringLength = 16; // Длина строки
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            enableUpperCase = random.nextBoolean();
            if (enableUpperCase) {
                buffer.append(Character.toUpperCase((char) randomLimitedInt));
                continue;
            }
            buffer.append((char) randomLimitedInt);
        }

        return buffer.toString();
    }

    /**
     * Получение формата входящего файла.
     *
     * @param file     Файл.
     * @param fileName Имя файла.
     * @return Формат файла.
     */
    private static Either<Failure, AbstractApplicationFormat> getFormat(File file,
                                                                        String fileName,
                                                                        Consumer<String> formatType,
                                                                        Function<String, AbstractApplicationFormat> switchHandler) {
        var expectedFailure = checkFileExtension(fileName, formatType);
        if (expectedFailure != null) {
            return Either.left(InvalidValue.INCORRECT_FORMAT);
        }
        try {
            var type
                    = new DefaultDetector().detect(TikaInputStream.get(Path.of(file.getPath())), new Metadata()).toString();
            AbstractApplicationFormat format = switchHandler.apply(type);
            return format == null ? Either.left(InvalidValue.INCORRECT_FORMAT)
                    : Either.right(format);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Either<Failure, AbstractApplicationFormat> getFileFormat(File file, String fileName) {
        return getFormat(file, fileName,
                FileFormat::valueOf,
                type -> switch (type) {
                    case "application/x-tika-msoffice", "application/msword" -> FileFormat.DOC;
                    case "application/vnd.openxmlformats-officedocument.wordprocessingml.document" -> FileFormat.DOCX;
                    case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" -> FileFormat.XLSX;
                    case "application/vnd.ms-excel" -> FileFormat.XLS;
                    case "application/pdf" -> FileFormat.PDF;
                    default -> null;
                });
    }

    public static Either<Failure, AbstractApplicationFormat> getContentFormat(File file, String fileName) {
        return getFormat(file, fileName,
                ContentFormat::valueOf,
                type -> switch (type) {
                    case "audio/x-flac" -> ContentFormat.FLAC;
                    case "audio/mpeg" -> ContentFormat.MP3;
                    case "audio/mp4" -> ContentFormat.M4A;
                    case "image/jpeg" -> ContentFormat.JPG;
                    case "image/png" -> ContentFormat.PNG;
                    case "video/x-msvideo" -> ContentFormat.AVI;
                    case "video/quicktime" -> ContentFormat.MP4;
                    default -> null;
                });
    }

    /**
     * Определение расширения файла по его имени.
     *
     * @param fileName Имя файла.
     * @return Ошибка валидации или null.
     */
    private static InvalidValue checkFileExtension(String fileName, Consumer<String> formatType) {
        String[] tmp = fileName.split("\\.");
        try {
            var candidate = tmp[tmp.length - 1].toUpperCase();
            formatType.accept(candidate);
            return null;
        } catch (IllegalArgumentException exception) {
            return InvalidValue.INCORRECT_FORMAT;
        }
    }

    /**
     * Генерация составного URL, состоящего из
     * случайной строки (для уникальности),
     * имени файла и идентификатора задачи.
     *
     * @param fileName Имя файла.
     * @param taskId   Идентификатор задачи.
     * @return Составной URL.
     */
    public static String composeUrl(String fileName, String taskId) {
        return getRandomString() +
                "-uploaded_task-" +
                taskId +
                "_" +
                fileName;
    }
}
