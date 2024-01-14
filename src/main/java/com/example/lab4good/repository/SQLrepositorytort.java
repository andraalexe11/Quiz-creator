package com.example.lab4good.repository;

import com.example.lab4good.domain.*;
import org.sqlite.SQLiteDataSource;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class SQLrepositorytort extends MemoryRepository<tort>{
    private static final String JDBC_URL = "jdbc:sqlite:Lab4Good.db";

    private Connection conn = null;

    public SQLrepositorytort() throws SQLException {
        openConnection();
        createSchema();
        try {
            initialize();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    private void initialize() throws SQLException, RepositoryException {
        if(this.list1.isEmpty()){
            tort tort1 = new tort(1, "ciocolata");
            this.add(tort1);
            tort tort2 = new tort(2, "vanilie");
            this.add(tort2);
            tort tort3 = new tort(3, "frisca");
            this.add(tort3);
            tort tort4 = new tort(4, "capsuni");
            this.add(tort4);
            tort tort5 = new tort(5, "fructe de padure");
            this.add(tort5);
            tort tort6 = new tort(6, "martipan");
            this.add(tort6);
            tort tort7 = new tort(7, "diplomat");
            this.add(tort7);
            tort tort8 = new tort(8, "morcovi");
            this.add(tort8);
            tort tort9 = new tort(9, "cirese");
            this.add(tort9);
            tort tort10 = new tort(10, "cafea");
            this.add(tort10);
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
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS cakes(id int, caketype varchar(20));");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] createSchema : " + e.getMessage());
        }
        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM cakes"); ResultSet rs = statement.executeQuery();) {
            while (rs.next()) {
                tort t = new tort(rs.getInt("id"), rs.getString("caketype"));
                super.add(t);
            }
        }catch (SQLException e){
            e.printStackTrace();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void add(tort t) throws SQLException, RepositoryException {
        super.add(t);
        try (PreparedStatement statement = conn.prepareStatement("INSERT INTO cakes VALUES (?, ?)")) {
            statement.setInt(1, t.getId());
            statement.setString(2, t.getTip());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<tort> getAll(){
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
            try (PreparedStatement statement = conn.prepareStatement("DELETE FROM cakes WHERE id=?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update(int id, tort newtort){
        try {
            super.update(id, newtort);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            try (PreparedStatement statement1 = conn.prepareStatement("DELETE FROM cakes WHERE id=?");
                 PreparedStatement statement2 = conn.prepareStatement("INSERT INTO cakes VALUES (?, ?)")){
                statement1.setInt(1, id);
                statement1.executeUpdate();
                statement2.setInt(1, id);
                statement2.setString(2, newtort.getTip());
                statement2.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

}



