package model;

import adobjects.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;

public class UserBase {
    private String dbPath;
    private static final String USERS_FILENAME = "users.txt";
    private static final String IDS_FILENAME = "ids.txt";

    public UserBase(String dbPath) {
        this.dbPath = dbPath;
    }

    public User checkExistance(String login, String password) throws IOException {
        Path path = FileSystems.getDefault().getPath(dbPath, USERS_FILENAME);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] fields = line.split(" ");
                if (fields[0].equals(login) && fields[1].equals(password)) {
                    try {
                        return new User(fields[0], Integer.parseInt(fields[2]), Integer.parseInt(fields[3]));
                    } catch (NumberFormatException n) {}
                }
            }
        }
        return null;
    }

    public void addUser(String login, String password, int userType) throws IOException {
        Path path = FileSystems.getDefault().getPath(dbPath, IDS_FILENAME);
        int id = Integer.parseInt(new String(Files.readAllBytes(path)));
        String idString = Integer.toString(id + 1);
        Files.write(path, idString.getBytes(StandardCharsets.UTF_8));
        path = FileSystems.getDefault().getPath(dbPath, USERS_FILENAME);
        String line = login + " " + password + " " + id + " " + userType + System.lineSeparator();
        Files.write(path, line.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
    }

    public User getUser(int userID) throws IOException {
        Path path = FileSystems.getDefault().getPath(dbPath, USERS_FILENAME);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] fields = line.split(" ");
                try {
                    if (Integer.parseInt(fields[2]) == userID) {
                        return new User(fields[0], Integer.parseInt(fields[2]), Integer.parseInt(fields[3]));
                    }
                } catch (NumberFormatException n) {}
            }
        }
        return null;
    }

    public User[] findShops(String login) throws IOException {
        ArrayList<User> matches = new ArrayList<>();
        Path path = FileSystems.getDefault().getPath(dbPath, USERS_FILENAME);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] fields = line.split(" ");
                if (fields[0].equals(login) && fields[3].equals("1")) {
                    matches.add(new User(fields[0], Integer.parseInt(fields[2]), Integer.parseInt(fields[3])));
                }
            }
        }
        return matches.toArray(new User[matches.size()]);
    }

    public boolean subscribe(int subscriber, int subscription){
        String name = "sub" + Integer.toString(subscriber) + ".txt";
        Path path = FileSystems.getDefault().getPath(dbPath, name);
        try {
            path.toFile().createNewFile();
        } catch (IOException i) {
            return false;
        }
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while((line = reader.readLine()) != null) {
                try {
                    if (Integer.parseInt(line) == subscription) {
                        return true;
                    }
                } catch (NumberFormatException n) {
                    System.out.println("cant parse int");
                }
            }
        }
        catch (IOException exc){
            return false;
        }
        try {
            String line = Integer.toString(subscription) + System.lineSeparator();
            Files.write(path, line.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            return true;
        }
        catch (IOException exc){
            return false;
        }
    }
}
