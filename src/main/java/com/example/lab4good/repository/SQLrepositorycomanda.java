package com.example.lab4good.repository;

import com.example.lab4good.domain.*;
import org.sqlite.SQLiteDataSource;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class SQLrepositorycomanda extends MemoryRepository<comanda>{
    private static final String JDBC_URL = "jdbc:sqlite:Lab4Good.db";

    private Connection conn = null;


    public SQLrepositorycomanda() {
        openConnection();
        createSchema();
        initialize();
    }

    private void initialize(){
        if(this.list1.isEmpty()) {
            ArrayList<tort> torturi = new ArrayList<>();
            tort tort1 = new tort(1, "ciocolata");
            torturi.add(tort1);
            tort tort2 = new tort(2, "vanilie");
            torturi.add(tort2);
            tort tort3 = new tort(3, "frisca");
            torturi.add(tort3);
            tort tort4 = new tort(4, "capsuni");
            torturi.add(tort4);
            tort tort5 = new tort(5, "fructe de padure");
            torturi.add(tort5);
            tort tort6 = new tort(6, "martipan");
            torturi.add(tort6);
            tort tort7 = new tort(7, "diplomat");
            torturi.add(tort7);
            tort tort8 = new tort(8, "morcovi");
            torturi.add(tort8);
            tort tort9 = new tort(9, "cirese");
            torturi.add(tort9);
            tort tort10 = new tort(10, "cafea");
            torturi.add(tort10);

            ArrayList<LocalDate> date = new ArrayList<>();
            date.add(LocalDate.of(2022, 9, 11));
            date.add(LocalDate.of(2023, 11, 28));
            date.add(LocalDate.of(2019, 7, 17));
            date.add(LocalDate.of(2017, 11, 25));
            date.add(LocalDate.of(2020, 9, 2));
            date.add(LocalDate.of(2021, 3, 5));
            date.add(LocalDate.of(2022, 11, 29));

            for(int i = 1; i <= 100; i++){
                int randomdate = new Random().nextInt(date.size());
                int randomcakenumber = new Random().nextInt(torturi.size());
                randomcakenumber ++;
                ArrayList<tort> torts = new ArrayList<>();
                for(int j = 0; j < randomcakenumber; j++){
                    int rdck = new Random().nextInt(torturi.size());
                    torts.add(torturi.get(rdck));
                }
                comanda comanda = new comanda(i, date.get(randomdate), torts);
                this.add(comanda);
            }
        }
    }
    private void openConnection() {
        try {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createSchema() {
        try {
            try (final Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS orders( id int, orderdate varchar(30), cakes varchar(250));");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] createSchema : " + e.getMessage());
        }
        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM orders"); ResultSet rs = statement.executeQuery();) {
            while (rs.next()) {
                String[] tokens = rs.getString("cakes").split(";");//cakes field contains : id1,type1;id2,type2
                ArrayList<tort> torturi = new ArrayList<>();
                for(int i = 0; i < tokens.length; i++){
                    String[] sep  = tokens[i].split(",");
                    tort t = new tort(Integer.parseInt(sep[0]), sep[1]);
                    torturi.add(t);
                }
                comanda c = new comanda(rs.getInt("id"), LocalDate.parse(rs.getString("orderdate")), torturi );
                super.add(c);
            }
        }catch (SQLException e){
            e.printStackTrace();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void add(comanda c)  {
        try {
            super.add(c);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sirtort = "";
        ArrayList<tort> torturi = c.getTorturi();
        for(tort t: torturi){
            sirtort = sirtort + String.valueOf(t.getId()) + "," + String.valueOf(t.getTip()) + ";";
        }
        try (PreparedStatement statement = conn.prepareStatement("INSERT INTO orders VALUES (?, ?, ?)")) {
            statement.setInt(1, c.getId());
            statement.setString(2,String.valueOf(c.getData()));
            statement.setString(3, sirtort);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public ArrayList<comanda> getAll() {
        try {
            return super.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id){
        try {
            super.remove(id);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        try {
            try (PreparedStatement statement = conn.prepareStatement("DELETE FROM orders WHERE id=?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, comanda newelem)  {
        try {
            super.update(id, newelem);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.remove(id);
        this.add(newelem);


    }
}