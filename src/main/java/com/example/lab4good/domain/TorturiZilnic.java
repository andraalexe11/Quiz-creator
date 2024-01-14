package com.example.lab4good.domain;

import java.time.LocalDate;

public class TorturiZilnic {
    private LocalDate ziua;
    private int nrtorturi;
    TorturiZilnic(){}
    public TorturiZilnic(LocalDate date, int nrtorturi){
        this.ziua = date;
        this.nrtorturi = nrtorturi;
    }

    public int getNrtorturi() {
        return nrtorturi;
    }

    public void setNrtorturi(int nrtorturi) {
        this.nrtorturi = nrtorturi;
    }

    public LocalDate getZiua() {
        return ziua;
    }

    public void setDate(LocalDate date) {
        this.ziua = date;
    }

    @Override
    public String toString() {
        return "TorturiZilnic{" +
                "ziua=" + ziua +
                ", nrtorturi=" + nrtorturi +
                '}';
    }
}
