package com.example.lab4good.domain;

public interface EntityConverter <T extends entity>{
    String toString(T object);
    T fromString(String line);
}
