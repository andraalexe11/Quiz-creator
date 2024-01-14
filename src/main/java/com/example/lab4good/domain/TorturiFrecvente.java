package com.example.lab4good.domain;

public class TorturiFrecvente {
    private int id;
    private String tip;
    private int nrcomenzi;

    public TorturiFrecvente(int id, String tip, int nrcomenzi) {
        this.id = id;
        this.tip = tip;
        this.nrcomenzi = nrcomenzi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getNrcomenzi() {
        return nrcomenzi;
    }

    public void setNrcomenzi(int nrcomenzi) {
        this.nrcomenzi = nrcomenzi;
    }

    @Override
    public String toString() {
        return "TorturiFrecvente{" +
                "id=" + id +
                ", tip='" + tip + '\'' +
                ", nrcomenzi=" + nrcomenzi +
                '}';
    }
}
