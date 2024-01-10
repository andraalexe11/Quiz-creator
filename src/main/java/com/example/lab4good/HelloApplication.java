package com.example.lab4good;

import com.example.lab4good.domain.*;
import com.example.lab4good.domain.tort;
import com.example.lab4good.repository.Irepository;
import com.example.lab4good.repository.MemoryRepository;
import com.example.lab4good.repository.RepositoryException;
import com.example.lab4good.service.servicecomanda;
import com.example.lab4good.service.servicetort;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class HelloApplication extends Application {
    static Irepository<tort> repotort = new Irepository<tort>() {
        @Override
        public void add(tort elem) throws RepositoryException, SQLException {

        }

        @Override
        public void remove(int id) throws RepositoryException {

        }

        @Override
        public tort find(int id) {
            return null;
        }

        @Override
        public ArrayList<tort> getAll() throws SQLException {
            return null;
        }

        @Override
        public void update(int idvechi, tort comandanoua) throws IOException {

        }

        @NotNull
        @Override
        public Iterator<tort> iterator() {
            return null;
        }
    };
    static Irepository<comanda> repocomanda = null;
    static tortConverter tortConverter = new tortConverter();
    static comandaConverter comandaConverter = new comandaConverter();
    static Settings settings = Settings.getInstance();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tort-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("COFETARIE");
        stage.setScene(scene);
        stage.show();
        if (Objects.equals(settings.getRepoType(), "memory")){
            repotort = new MemoryRepository<tort>();
            repocomanda = new MemoryRepository<comanda>();
        }
        servicetort servicetort = new servicetort(repotort);
        servicecomanda servicecomanda = new servicecomanda(repocomanda);

        CofetarieController cofetarieController = fxmlLoader.getController();
        cofetarieController.setService(servicecomanda, servicetort);

    }

    public static void main(String[] args) {
        launch();
    }
}