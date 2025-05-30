package com.helltractor.demo.user;

import com.helltractor.demo.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class UserFileBatch implements Iterable<List<User>> {
    
    private final File file;
    private int batchSize = 10;
    
    public UserFileBatch(File file) {
        this.file = file;
    }
    
    public UserFileBatch(File file, int batchSize) {
        this.file = file;
        this.batchSize = batchSize;
    }
    
    @Override
    public Iterator<List<User>> iterator() {
        return new UserFileBatchIterator();
    }
    
    private class UserFileBatchIterator implements Iterator<List<User>> {
        
        private boolean finished = false;
        private final BufferedReader reader;
        private List<User> batchUserList = new ArrayList<>();
        
        UserFileBatchIterator() {
            try {
                this.reader = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                throw new RuntimeException("File not found: " + file.getAbsolutePath(), e);
            }
        }
        
        private void readUsersFromFile() throws IOException {
            batchUserList.clear();
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
            return !finished;
        }
        
        @Override
        public List<User> next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more users available");
            }
            try {
                readUsersFromFile();
            } catch (IOException e) {
                throw new RuntimeException("Error reading users from file", e);
            }
            return batchUserList;
        }
    }
    
}
