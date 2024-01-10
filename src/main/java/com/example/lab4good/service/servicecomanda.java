package com.example.lab4good.service;

import com.example.lab4good.domain.*;
import com.example.lab4good.repository.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


public class servicecomanda {
    public Irepository<comanda> repocomanda;
    public servicecomanda(){}
    public servicecomanda(Irepository<comanda> repocomanda){
        this.repocomanda = repocomanda;
    }
    public void add(int id, LocalDate data, ArrayList<tort>  torturi) throws RepositoryException, SQLException {
        comanda comanda = new comanda(id, data, torturi);
        repocomanda.add(comanda);
    }
    public ArrayList<comanda> getAll()throws RepositoryException {
        try {
            return repocomanda.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) throws RepositoryException {
        repocomanda.remove(id);
    }

    public void update(int idvechi, LocalDate datanoua, ArrayList<tort> torturinoi) throws RepositoryException, IOException {
        comanda comandanoua  =  new comanda(idvechi, datanoua, torturinoi);
        repocomanda.update(idvechi, comandanoua);
    }
    public comanda getbyId(int id){
        return repocomanda.find(id);
    }

}
