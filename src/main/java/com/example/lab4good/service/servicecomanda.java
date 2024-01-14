package com.example.lab4good.service;

import com.example.lab4good.domain.*;
import com.example.lab4good.repository.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;


public class servicecomanda {
    public Irepository<comanda> repocomanda;
    public servicecomanda(){}
    public servicecomanda(Irepository<comanda> repocomanda){
        this.repocomanda = repocomanda;
        initialize();
    }
    void initialize(){

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

    public ArrayList<TorturiZilnic> countdailycakes() throws RepositoryException {
        ArrayList<TorturiZilnic> list = new ArrayList<>();
        ArrayList<LocalDate> useddates = new ArrayList<>();
        ArrayList<comanda> comenzi  = this.getAll();
        for(var comanda: comenzi){
            LocalDate date = comanda.getData();
            if(!useddates.contains(date)){
                int nr = (int)comenzi.stream().filter(x->x.getData() == date).count();
                list.add(new TorturiZilnic(date, nr));
                useddates.add(date);

            }else {
                int index = useddates.indexOf(date);
                TorturiZilnic tz = list.get(index);
                list.remove(index);
                tz.setNrtorturi(tz.getNrtorturi() + 1);
                list.add(tz);

            }

        }
        list.sort(Comparator.comparing(TorturiZilnic::getNrtorturi).reversed());
        return list;
    }

    public  ArrayList<TorturiLunar> countmonthlycakes() throws RepositoryException {
        ArrayList<TorturiLunar> list = new ArrayList<>();
        for(int i = 1; i <=12; i++){
            int month = i;
            list.add(new TorturiLunar(i, this.getAll().stream().filter(x->x.getData().getMonth() == Month.of(month)).toArray().length));
        }
        list.sort(Comparator.comparing(TorturiLunar::getNrtorturi).reversed());
        return list;
    }

    public ArrayList<TorturiFrecvente> frequentcakes() throws RepositoryException {
        ArrayList<TorturiFrecvente> list = new ArrayList<>();
        ArrayList<comanda> allItems = this.getAll();
        ArrayList<tort> usedcakes = new ArrayList<>();
        for (var item : allItems) {
            ArrayList<tort> torts = item.getTorturi();
            for (var tort : torts) {
                if(!usedcakes.contains(tort)){
                    int numberOfOccurrences = (int) allItems.stream().filter(x -> x.getTorturi().contains(tort)).count();
                    list.add(new TorturiFrecvente(tort.getId(), tort.getTip(), numberOfOccurrences));
                    usedcakes.add(tort);
                }else {
                    int index = usedcakes.indexOf(tort);
                    TorturiFrecvente tf = list.get(index);
                    list.remove(index);
                    tf.setNrcomenzi(tf.getNrcomenzi() + 1);
                    list.add(tf);
                }
            }
        }

        list.sort(Comparator.comparing(TorturiFrecvente::getNrcomenzi).reversed());
        return list;

    }
}
