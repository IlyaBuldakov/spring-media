package com.htc.util;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValue;
import com.htc.domain.entities.file.File.Format;
import io.vavr.control.Either;
import org.apache.tika.Tika;
import java.util.Random;

public class FileHelper {

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

    public static Either<Failure, Format> getFormat(byte[] fileBinary, String fileName) {
        var expectedFailure = checkFileExtension(fileName);
        if (expectedFailure != null) {
            return Either.left(InvalidValue.INCORRECT_FORMAT);
        }
        Format format = null;
        Tika tika = new Tika();
        switch (tika.detect(fileBinary)) {
            case "application/x-tika-msoffice", "application/msword" -> format = Format.DOC;
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document" -> format = Format.DOCX;
            case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" -> format = Format.XLSX;
            case "application/vnd.ms-excel" -> format = Format.XLS;
            case "application/pdf" -> format = Format.PDF;
        }
        return format == null ? Either.left(InvalidValue.INCORRECT_FORMAT)
                : Either.right(format);
    }

    private static InvalidValue checkFileExtension(String fileName) {
        String[] tmp = fileName.split("\\.");
        try {
            Format.valueOf(tmp[tmp.length - 1].toUpperCase());
            return null;
        } catch (IllegalArgumentException exception) {
            return InvalidValue.INCORRECT_FORMAT;
        }
    }
}
