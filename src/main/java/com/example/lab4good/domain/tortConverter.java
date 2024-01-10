package com.example.lab4good.domain;

public class tortConverter implements EntityConverter<tort>{

    @Override
    public String toString(tort object) {
        return object.getId() + "," + object.getTip();
    }

    @Override
    public tort fromString(String line) {
        String[] tokens = line.split(",");
        return new tort(Integer.parseInt(tokens[0]), tokens[1]);
    }
}