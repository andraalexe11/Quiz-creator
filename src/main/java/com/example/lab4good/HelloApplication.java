package com.example.lab4good;

import com.example.lab4good.domain.*;
import com.example.lab4good.domain.tort;
import com.example.lab4good.repository.*;
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
    static Irepository<tort> repotort = null;

    static Irepository<comanda> repocomanda = null;
    static tortConverter tortConverter = new tortConverter();
    static comandaConverter comandaConverter = new comandaConverter();
    static Settings settings = Settings.getInstance();
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tort-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("COFETARIE");
        stage.setScene(scene);
        stage.show();
        if (Objects.equals(settings.getRepoType(), "memory")){
            repotort = new MemoryRepository<tort>();
            repocomanda = new MemoryRepository<comanda>();
        }
        if(Objects.equals(settings.getRepoType(),"text")){
            /*
            repo_type: text
            repo_file1: C:\Users\Alexe Andra\Documents\Java\Lab4Good\src\main\java\com\example\lab4good\torturi.txt
            repo_file2: C:\Users\Alexe Andra\Documents\Java\Lab4Good\src\main\java\com\example\lab4good\comenzi.txt
             */
            repotort = new TextFileRepository<tort>(settings.getRepoFile1(), tortConverter);
            repocomanda = new TextFileRepository<comanda>(settings.getRepoFile2(), comandaConverter);

        }

        if(Objects.equals(settings.getRepoType(), "bin")){
            /*repo_type: bin
            repo_file1: C:\\Users\\Alexe Andra\\Documents\\Java\\Lab4Good\\src\\main\\java\\com\\example\\lab4good\\torturi.bin
            repo_file2: C:\\Users\\Alexe Andra\\Documents\\Java\\Lab4Good\\src\\main\\java\\com\\example\\lab4good\\comenzi.bin
             */
            repotort = new BinaryFileRepository<tort>(settings.getRepoFile1());
            repocomanda = new BinaryFileRepository<comanda>(settings.getRepoFile2());
        }
        if (Objects.equals(settings.getRepoType(), "db")) {
            repotort = new SQLrepositorytort();
            repocomanda = new SQLrepositorycomanda();

        }

        servicetort servicetort = new servicetort(repotort);
        servicecomanda servicecomanda = new servicecomanda(repocomanda);

        CofetarieController cofetarieController = fxmlLoader.getController();
        cofetarieController.setService(servicecomanda, servicetort);
       //ui ui = new ui(servicetort, servicecomanda);
       //ui.menu();
    }

    public static void main(String[] args) {
        launch();
    }
}