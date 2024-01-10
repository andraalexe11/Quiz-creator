package com.example.lab4good.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class comanda extends entity implements Serializable {
    private LocalDate data;
    private ArrayList<tort> torturi;

    public comanda() {
    }

    public comanda(int id, LocalDate data, ArrayList<tort> torturi) {
        setId(id);
        this.data = data;
        this.torturi = torturi;
    }

    public LocalDate getData() {
        return this.data;
    }

    public ArrayList<tort>  getTorturi() {
        return torturi;
    }



    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setTorturi(ArrayList<tort>  torturi) {
        this.torturi = torturi;
    }

    @Override
    public String toString() {
        return "com.example.lab4.domain.comanda{" +
                "data=" + data +
                ",torturi=" + torturi +
                '}';
    }
}
