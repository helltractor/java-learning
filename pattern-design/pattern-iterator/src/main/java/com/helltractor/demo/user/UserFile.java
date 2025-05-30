package com.helltractor.demo.user;

import com.helltractor.demo.model.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class UserFile implements Iterable<User> {
    
    private final File file;
    
    public UserFile(File file) {
        this.file = file;
    }
    
    @Override
    public Iterator<User> iterator() {
        return new UserFileIterator();
    }
    
    private class UserFileIterator implements Iterator<User> {
        
        private int index = 0;
        private List<User> userList = new ArrayList<>();
        
        UserFileIterator() {
            try {
                userList = readUsersFromFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        private List<User> readUsersFromFile() throws IOException {
            try {
                return Files.readAllLines(file.toPath()).stream().
                        map(line -> {
                            String midString = line.substring(1, line.length() - 1);
                            String[] parts = midString.split(",");
                            String name = parts[0].trim();
                            int age = Integer.parseInt(parts[1].trim());
                            return new User(name, age);
                        }).collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }
        
        @Override
        public boolean hasNext() {
            return index < userList.size();
        }
        
        @Override
        public User next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more users available");
            }
            return userList.get(index++);
        }
    }
    
}
