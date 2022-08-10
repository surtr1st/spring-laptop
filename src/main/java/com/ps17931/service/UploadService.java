package com.ps17931.service;

import java.io.File;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {

    private final String resourcePath = System.getProperty("user.dir");

    public File save(MultipartFile file, String folder1, String folder2) {
		String root = resourcePath + "/src/main/resources/static/assets/" + folder1 + "/" + folder2 + "/";
		File dir = new File(root);

        if(!dir.exists())
            dir.mkdirs();

        try {
            File saveFile = new File(dir, Objects.requireNonNull(file.getOriginalFilename()));
            file.transferTo(saveFile);
            return saveFile;
        }
        catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
