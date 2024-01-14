package com.example.lab4good.repository;

import com.example.lab4good.domain.EntityConverter;
import com.example.lab4good.domain.entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

public class TextFileRepository<T extends entity> extends MemoryRepository<T> {

    private String fileName;

    private EntityConverter<T> converter;

    public TextFileRepository(String fileName, EntityConverter<T> converter) {
        this.fileName = fileName;
        this.converter = converter;

        try {
            loadFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(T o) throws RepositoryException, SQLException {
        super.add(o);
        try {
            saveFile();
        } catch (IOException e) {
            throw new RepositoryException("Error saving object", e);
        }
    }

    private void saveFile() throws IOException {
        // TODO File is rewritten at each modification :'(
        try (FileWriter fw = new FileWriter(fileName)) {
            for (T object : list1) {
                fw.write(converter.toString(object));
                fw.write("\r\n");
            }
        }
    }

    private void loadFile() throws IOException {
        // delete whatever is in the repo's list
        list1.clear();

        // BufferedReader - reads data ahead into a buffer :)
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            while (line != null && !line.isEmpty()) {
                list1.add(converter.fromString(line));
                line = br.readLine();
            }
        }
    }

    @Override
    public void update(int id, T newelem) throws IOException {
        super.update(id, newelem);
        saveFile();
    }

    @Override
    public void remove(int id) throws RepositoryException {
        super.remove(id);
        try {
            saveFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
