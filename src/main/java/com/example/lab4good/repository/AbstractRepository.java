package com.example.lab4good.repository;

import com.example.lab4good.domain.entity;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class AbstractRepository<T extends entity> implements Irepository<T> {
    protected ArrayList<T> list1 = new ArrayList<T>();


    @Override
    public Iterator<T> iterator() {
        return new ArrayList<T>(list1).iterator();
    }

}
