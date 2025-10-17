package org.example.user.service;

import org.example.controller.servlet.exception.NotFoundException;
import org.example.user.entity.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class UserAvatarService {
    /**
     * Path to folder where avatars are stored.
     */
    private final String fileStorePath;

    public UserAvatarService(String fileStorePath) {
        this.fileStorePath = fileStorePath;
    }

    public byte[] getAvatar(User user) throws IOException {
        if (user.getAvatarPath() == null) {
            throw new NotFoundException();
        }
        Path folderPath = Paths.get(user.getAvatarPath());
        try {
            return Files.readAllBytes(folderPath);
        } catch (IOException e) {
            throw new IOException("Error reading avatar", e);
        }
    }

    public void saveAvatar(User user, String avatar) throws IOException {
        Path rootPath = Paths.get(this.fileStorePath);
        if (!Files.exists(rootPath)) {
            Files.createDirectory(rootPath);
        }


    }

    public void deleteAvatar(User user) throws IOException {
        Path folderPath = Paths.get(this.fileStorePath).resolve(user.getId().toString() + ".png");
        try {
            Files.delete(folderPath);
            user.setAvatarPath(null);
        } catch (IOException e) {
            throw new IOException("Error deleting avatar", e);
        }
    }
}