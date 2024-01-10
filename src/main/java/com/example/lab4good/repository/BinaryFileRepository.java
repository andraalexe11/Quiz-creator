package com.example.lab4good.repository;

import com.example.lab4good.domain.entity;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class BinaryFileRepository<T extends entity> extends MemoryRepository<T> {
    private String fileName;

    public BinaryFileRepository(String fileName) {
//        super();
        this.fileName = fileName;
        try {
            loadFile();
        } catch (IOException | ClassNotFoundException e) {
            // not very good practice
            throw new RuntimeException(e);
        }
    }


    @Override
    public void add(T o) throws RepositoryException, SQLException {
        super.add(o);
        // saveFile se executa doar daca super.add() nu a aruncat exceptie
        try {
            saveFile();
        } catch (IOException e) {
            throw new RepositoryException("Error saving file " + e.getMessage(), e);
        }
    }

    @Override
    public void remove(int id) throws RepositoryException {
        super.remove(id);
    }

    private void loadFile() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(fileName));
            this.list1 = (ArrayList<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Repo starting a new file");
        } finally {
            if (ois != null)
                ois.close();
        }


    }


    private void saveFile() throws IOException {
        // try-with-resources
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(list1);
        }
    }
}

