package com.example.lab4good.domain;

public class TorturiLunar {
    private int luna;
    private int nrtorturi;

    public TorturiLunar(int luna, int nrtorturi) {
        this.luna = luna;
        this.nrtorturi = nrtorturi;

    }

    public int getLuna() {
        return luna;
    }

    public void setLuna(int luna) {
        this.luna = luna;
    }

    public int getNrtorturi() {
        return nrtorturi;
    }

    public void setNrtorturi(int nrtorturi) {
        this.nrtorturi = nrtorturi;
    }

    @Override
    public String toString() {
        return "TorturiLunar{" +
                "luna=" + luna +
                ", nrtorturi=" + nrtorturi +
                '}';
    }
}
