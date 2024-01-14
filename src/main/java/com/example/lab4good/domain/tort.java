package com.example.lab4good.domain;

import java.io.Serializable;
import java.util.Objects;

public class tort extends entity implements Serializable {
    private String tip;
    public tort(){}
    public tort(int id, String tip){
        setId(id);
        this.tip = tip;
    }
    public String getTip(){
        return this.tip;
    }
    public void setTip(String tip){
        this.tip = tip;
    }

    @Override
    public String toString() {
        return "id=" + getId() +
                ",tip=" + tip +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof tort tort)) return false;
        return Objects.equals(getTip(), tort.getTip());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTip());
    }
}
