package com.example.lab4good;

import com.example.lab4good.domain.*;
import com.example.lab4good.repository.RepositoryException;
import javafx.application.Application;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import com.example.lab4good.service.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CofetarieController extends Application {

    @FXML
    Label welcomeText = new Label();
    private servicetort servicetort ;
    private servicecomanda servicecomanda;
    DateTimeFormatter form = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // tabel torturi
    @FXML
    TableView<tort> tableViewTort;
    @FXML
    TableColumn<tort, String> tableColumnIdtort;
    @FXML
    TableColumn<tort, String> tableColumnTiptort;


    //tabel comenzi
    @FXML
    TableView<comanda> tableViewcomanda;
    @FXML
    TableColumn<comanda, String> tableColumnIdcomanda;
    @FXML
    TableColumn<comanda, String> tableColumnDatacomanda;
    @FXML
    TableColumn<comanda, String> tableColumnTorturicomanda;

    //tabel raport torturi/zile
    @FXML
    TableView<TorturiZilnic> tableViewtortzilnic;
    @FXML
    TableColumn<TorturiZilnic, String> tableColumndate;
    @FXML
    TableColumn<TorturiZilnic, String> nrTorturi;

    //tabel raport torturi/luni
    @FXML
    TableView<TorturiLunar> tableViewtortlunar;
    @FXML
    TableColumn<TorturiLunar, String> tableColumnluna;
    @FXML
    TableColumn<TorturiLunar, String> tableColumnnrlunar;
    //tabel raport
    @FXML
    TableView<TorturiFrecvente> tableViewtortfrecvent;
    @FXML
    TableColumn<TorturiFrecvente, String> idtortfrecvent;
    @FXML
    TableColumn<TorturiFrecvente, String> tiptortfrecvent;
    @FXML
    TableColumn<TorturiFrecvente, String> nrcomenzitorturifrecvente;


    //Adaugare tort

    @FXML
    TextField tortIdField;
    @FXML
    TextField tortTipField;

    //Adaugare comanda

    @FXML
    TextField comandaIdField;
    @FXML
    TextField comandaDataField;
    @FXML
    TextField comandaTorturiField;

    //Stergere tort

    @FXML
    TextField stergetortIDfield;

    //Stergere comanda

    @FXML
    TextField stergecomandaIDfield;

    //Update tort

    @FXML
    TextField updatetorttIdfield;
    @FXML
    TextField updatetortnewtipfield;

    //Update comanda

    @FXML
    TextField updatecomandaIDfield;
    @FXML
    TextField updatecomandanewdatafield;
    @FXML
    TextField updatecomandanewtorturifield;

    ObservableList<tort> modelTort = FXCollections.observableArrayList();
    ObservableList<comanda> modelComanda = FXCollections.observableArrayList();
    ObservableList<TorturiZilnic> modeltorturizilnic = FXCollections.observableArrayList();
    ObservableList<TorturiLunar> modeltorturilunar = FXCollections.observableArrayList();
    ObservableList<TorturiFrecvente> modeltorturifrecvente = FXCollections.observableArrayList();
    public void setService(servicecomanda servicecomanda, servicetort servicetort){
        this.servicecomanda = servicecomanda;
        this.servicetort = servicetort;
        initModel();
    }
    @FXML
    public void initialize(){
        try {
            tableColumnIdtort.setCellValueFactory(new PropertyValueFactory<tort, String>("Id"));
            tableColumnTiptort.setCellValueFactory(new PropertyValueFactory<tort, String>("Tip"));
            tableViewTort.setItems(modelTort);

            tableColumnIdcomanda.setCellValueFactory(new PropertyValueFactory<comanda, String>("Id"));
            tableColumnDatacomanda.setCellValueFactory(new PropertyValueFactory<comanda, String>("Data"));
            tableColumnTorturicomanda.setCellValueFactory(new PropertyValueFactory<comanda, String>("Torturi"));
            tableViewcomanda.setItems(modelComanda);

            tableColumndate.setCellValueFactory(new PropertyValueFactory<TorturiZilnic, String>("Ziua"));
            nrTorturi.setCellValueFactory(new PropertyValueFactory<TorturiZilnic, String>("Nrtorturi"));
            tableViewtortzilnic.setItems(modeltorturizilnic);

            tableColumnluna.setCellValueFactory(new PropertyValueFactory<TorturiLunar, String>("Luna"));
            tableColumnnrlunar.setCellValueFactory(new PropertyValueFactory<TorturiLunar, String>("Nrtorturi"));
            tableViewtortlunar.setItems(modeltorturilunar);

            idtortfrecvent.setCellValueFactory(new PropertyValueFactory<TorturiFrecvente, String>("Id"));
            tiptortfrecvent.setCellValueFactory(new PropertyValueFactory<TorturiFrecvente, String>("Tip"));
            nrcomenzitorturifrecvente.setCellValueFactory(new PropertyValueFactory<TorturiFrecvente, String>("Nrcomenzi"));
            tableViewtortfrecvent.setItems(modeltorturifrecvente);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initModel() {
        modelTort.setAll(servicetort.getAll());
        try {
            modelComanda.setAll(servicecomanda.getAll());
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        try {
            modeltorturizilnic.setAll(servicecomanda.countdailycakes());
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        try {
            modeltorturilunar.setAll(servicecomanda.countmonthlycakes());
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        try {
            modeltorturifrecvente.setAll(servicecomanda.frequentcakes());
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }


    public void onHelloButtonClick(ActionEvent actionEvent) {
        welcomeText.relocate(100,100);
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void handleAddTort(ActionEvent actionEvent){
        try {
            int id = Integer.parseInt(tortIdField.getText());
            String tip = tortTipField.getText();
            servicetort.add(id, tip);

            initialize();
            initModel();

        }catch (RuntimeException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Adaugare tort");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (RepositoryException e) {
           Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Adaugare tort");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Adaugare tort");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        tortIdField.clear();
        tortTipField.clear();
    }

    public void handleAddComanda(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(comandaIdField.getText());
            LocalDate date = LocalDate.parse(comandaDataField.getText(), form);
            String cakes = comandaTorturiField.getText();
            String[] tokens = cakes.split(";");
            ArrayList<tort> torturi = new ArrayList<>();
            for (int i = 0; i < tokens.length; i++) {
                String[] sep = tokens[i].split(",");
                tort t = new tort(Integer.parseInt(sep[0]), sep[1]);
                torturi.add(t);
            }
            servicecomanda.add(id, date, torturi);
            initialize();
            initModel();
        }catch (RuntimeException | RepositoryException | SQLException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Adaugare comanda");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        comandaIdField.clear();
        comandaDataField.clear();
        comandaTorturiField.clear();
    }

    public void handleDeleteTort(ActionEvent actionEvent){
        try {
            int id = Integer.parseInt(stergetortIDfield.getText());
            servicetort.delete(id);
            initialize();
            initModel();

        }catch (RuntimeException | RepositoryException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Stergere tort");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        stergetortIDfield.clear();
        stergetortIDfield.clear();
    }

    public void handleDeleteComanda(ActionEvent actionEvent){
        try{
            int id = Integer.parseInt(stergecomandaIDfield.getText());
            servicecomanda.delete(id);
            initialize();
            initModel();
        }catch (RuntimeException | RepositoryException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Stergere comanda");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

        stergecomandaIDfield.clear();
    }

    public void handleUpdateTort(ActionEvent actionEvent){
        try {
            int id = Integer.parseInt(updatetorttIdfield.getText());
            String tipnou = updatetortnewtipfield.getText();
            servicetort.update(id, tipnou);
            initialize();
            initModel();
        }catch (RuntimeException | RepositoryException | IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Update tort");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        updatetortnewtipfield.clear();
        updatetorttIdfield.clear();
    }

    public void handleUpdateComanda(ActionEvent actionEvent){
        try{
            int id = Integer.parseInt(updatecomandaIDfield.getText());
            LocalDate data = LocalDate.parse(updatecomandanewdatafield.getText(), form);
            String cakes = comandaTorturiField.getText();
            String[] tokens = cakes.split(";");
            ArrayList<tort> torturi = new ArrayList<>();
            for (int i = 0; i < tokens.length; i++) {
                String[] sep = tokens[i].split(",");
                tort t = new tort(Integer.parseInt(sep[0]), sep[1]);
                torturi.add(t);
            }
            servicecomanda.update(id, data, torturi);
            initialize();
            initModel();
        }catch (RuntimeException | RepositoryException | IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Update comanda");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        updatecomandaIDfield.clear();
        updatecomandanewdatafield.clear();
        updatecomandanewtorturifield.clear();

    }



    @Override
    public void start(Stage stage) throws Exception {

    }
}
