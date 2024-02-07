package com.rybina.http.service;

import com.rybina.http.util.PropertiesUtil;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

public class ImageService {

    private static final ImageService INSTANCE = new ImageService();

    public final String basePath = PropertiesUtil.get("image.base.url");

    private ImageService() {
    }

    public static ImageService getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    public void upload(String imagePath, InputStream imageContent) {
        Path imageFullPath = Path.of(basePath, imagePath);

        try (imageContent) {
            Files.createDirectories(imageFullPath.getParent());
//            Создать если его нет, переписать есть существует
            Files.write(imageFullPath, imageContent.readAllBytes(), CREATE, TRUNCATE_EXISTING);
        }
    }

    @SneakyThrows
    public Optional<InputStream> get(String imagePath) {
        Path imageFullPath = Path.of(basePath, imagePath);
        return Files.exists(imageFullPath)
                ? Optional.of(Files.newInputStream(imageFullPath))
                : Optional.empty();
    }
}
