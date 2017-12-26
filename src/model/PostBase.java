package model;

import adobjects.Post;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class PostBase {
    private static final String IDS_FILENAME = "ids.txt";
    private String dbPath;

    public PostBase(String dbPath) {
        this.dbPath = dbPath;
    }

    public void addPost(Post post) throws IOException {
        Path path = FileSystems.getDefault().getPath(dbPath, IDS_FILENAME);
        int id = Integer.parseInt(new String(Files.readAllBytes(path)));
        String idString = Integer.toString(id + 1);
        Files.write(path, idString.getBytes(StandardCharsets.UTF_8));
        path = FileSystems.getDefault().getPath(dbPath, Integer.toString(id) + ".txt");
        Files.write(path, (post.getHeader() + System.lineSeparator()).getBytes(StandardCharsets.UTF_8));
    }

    public Post getPost(int postID) throws IOException {
        Path path = FileSystems.getDefault().getPath(dbPath, Integer.toString(postID) + ".txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = reader.readLine();
            return new Post(line);
        }
    }
}
