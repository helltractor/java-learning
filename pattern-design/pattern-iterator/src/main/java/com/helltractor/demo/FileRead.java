package com.helltractor.demo;

import com.helltractor.demo.model.User;
import com.helltractor.demo.user.UserFile;
import com.helltractor.demo.user.UserFileBatch;
import com.helltractor.demo.user.UserFileBatchRead;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FileRead {
    
    public static void main(String[] args) throws IOException {
        List<User> userList = new ArrayList<>();
        
        readUser((user) -> {
            System.out.println(user.toString());
            userList.add(user);
        });
        
        File file = new File("user.txt");
        UserFile userFile = new UserFile(file);
        for (User user : userFile) {
            System.out.println(user.toString());
        }
        
        UserFileBatch userFileBatch = new UserFileBatch(file);
        for (List<User> batch : userFileBatch) {
            for (User user : batch) {
                System.out.println(user.toString());
            }
        }
        
        UserFileBatchRead userFileBatchRead = new UserFileBatchRead(file);
        for (User user : userFileBatchRead) {
            System.out.println(user.toString());
        }
    }
    
    private static void readUser(Consumer<User> consumer) throws IOException {
        List<String> lines = Files.readAllLines(new File("user.txt").toPath());
        for (String line : lines) {
            String midString = line.substring(1, line.length() - 1);
            String[] parts = midString.split(",");
            String name = parts[0].trim();
            int age = Integer.parseInt(parts[1].trim());
            User user = new User(name, age);
            consumer.accept(user);
        }
    }
    
}
