package com.example.lab4good.service;
import com.example.lab4good.domain.*;
import com.example.lab4good.repository.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class servicetort {
    public Irepository<tort> repotort;

    public servicetort() {
    }

    public servicetort(Irepository<tort> repot) {
        this.repotort = repot;
    }

    public void add(int id, String tip) throws RepositoryException, SQLException {
        tort tort = new tort(id, tip);
        repotort.add(tort);
    }

    public void delete(int id) throws RepositoryException {
        repotort.remove(id);
    }

    public ArrayList<tort> getAll() {
        try {
            return repotort.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int idvechi, String tipnou) throws RepositoryException, IOException {
        tort tortnou = new tort(idvechi, tipnou);
        repotort.update(idvechi, tortnou);

    }
    public tort getbyId(int id){
        return repotort.find(id);
    }


}
