package com.example.lab4good.repository;

import com.example.lab4good.DuplicateObjectException;
import com.example.lab4good.domain.entity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemoryRepository <T extends entity> extends AbstractRepository<T> {
    public void add(T elem) throws RepositoryException, SQLException {
        if (elem == null) {
            throw new IllegalArgumentException();
        }
        if(this.find(elem.getId()) != null){
            throw new DuplicateObjectException("Cannot duplicate com.example.lab4.repository objects!");
        }
        this.list1.add(elem);
    }

    public T find(int id) {
        for(T elem : list1){
            if(elem.getId() == id) return elem;
        }
        return null;
    }

    public void remove(int id) throws RepositoryException {
        if(this.find(id) == null){
            throw new RuntimeException("Element does not exist!");
        }
        int i = list1.indexOf(find(id));
        list1.remove(i);
    }

    public ArrayList<T> getAll() throws SQLException {
        if(this.list1.isEmpty()){
            throw new RuntimeException("The list is empty!");
        }
        return this.list1;
    }

    public void update(int id, T newelem) throws IOException {
        if(find(id) == null){
            throw new RuntimeException("Element does not exist!");
        }
        int i = list1.indexOf(find(id));
        try {
            this.remove(id);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        this.list1.add(i,newelem);
    }
}

