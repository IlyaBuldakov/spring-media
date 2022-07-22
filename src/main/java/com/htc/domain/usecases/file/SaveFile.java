package com.htc.domain.usecases.file;

import org.springframework.stereotype.Component;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class SaveFile {

    public void execute(byte[] fileBinary, String composedUrl) {
            try {
                var path = Files.createDirectories(Paths.get("src/main/webapp/uploads/files"));
                new File(path + composedUrl);
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(composedUrl));
                stream.write(fileBinary);
                stream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
}
