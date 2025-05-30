package com.helltractor.demo.user;

import com.helltractor.demo.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class UserFileBatchRead implements Iterable<User> {
    
    private final File file;
    private int batchSize = 10;
    
    public UserFileBatchRead(File file) {
        this.file = file;
    }
    
    public UserFileBatchRead(File file, int batchSize) {
        this.file = file;
        this.batchSize = batchSize;
    }
    
    @Override
    public Iterator<User> iterator() {
        return new UserFileReadBatchIterator();
    }
    
    private class UserFileReadBatchIterator implements Iterator<User> {
        
        private final BufferedReader reader;
        private int index = 0;
        private boolean finished = false;
        private final List<User> batchUserList = new ArrayList<>();
        
        UserFileReadBatchIterator() {
            try {
                this.reader = new BufferedReader(new FileReader(file));
                readUsersFromFile();
            } catch (FileNotFoundException e) {
                throw new RuntimeException("File not found: " + file.getAbsolutePath(), e);
            }
        }
        
        private void readUsersFromFile() {
            batchUserList.clear();
            index = 0;
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    String midString = line.substring(1, line.length() - 1);
                    String[] parts = midString.split(",");
                    String name = parts[0].trim();
                    int age = Integer.parseInt(parts[1].trim());
                    batchUserList.add(new User(name, age));
                    if (batchUserList.size() == batchSize) {
                        break;
                    }
                }
                if (batchUserList.isEmpty()) {
                    reader.close();
                    finished = true;
                }
            } catch (IOException e) {
                finished = true;
                try {
                    reader.close();
                } catch (IOException closeException) {
                    throw new RuntimeException("Error closing reader", closeException);
                }
            }
        }
        
        @Override
        public boolean hasNext() {
            if (index >= batchUserList.size() && !finished) {
                try {
                    readUsersFromFile();
                } catch (Exception e) {
                    throw new RuntimeException("Error reading users from file", e);
                }
            }
            return index < batchUserList.size();
        }
        
        @Override
        public User next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more users available");
            }
            return batchUserList.get(index++);
        }
    }
    
}
