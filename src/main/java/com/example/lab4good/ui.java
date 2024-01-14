package com.example.lab4good;

import com.example.lab4good.repository.*;
import com.example.lab4good.domain.*;
import com.example.lab4good.service.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ui {
    private servicecomanda servicecomanda = new servicecomanda();
    private servicetort servicetort = new servicetort();

    Scanner choise = new Scanner(System.in);
    DateTimeFormatter form = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    ui() {
    }

    ui(servicetort servicetort, servicecomanda servicecomanda) {
        this.servicetort = servicetort;
        this.servicecomanda = servicecomanda;
    }

    void addtort() throws RepositoryException, SQLException {
        System.out.print("Enter cake id: ");
        String token = choise.next();
        int id = Integer.parseInt(token);
        System.out.print("Enter cake type: ");
        String tip = choise.next();
        servicetort.add(id, tip);
    }

    void deletetort() throws RepositoryException {
        System.out.print("Enter cake id: ");
        String token = choise.next();
        int id = Integer.parseInt(token);
        servicetort.delete(id);
    }

    void updatetort() throws RepositoryException, IOException {
        System.out.print("Enter cake's id: ");
        String token = choise.next();
        int idvechi = Integer.parseInt(token);
        System.out.print("Enter new cake type: ");
        String tipnou = choise.next();
        servicetort.update(idvechi, tipnou);

    }

    void getAlltorturi() throws RepositoryException {
        for(int i = 0; i<servicetort.getAll().size(); i++){
            System.out.println("Cake:  " + servicetort.getAll().get(i));
        }
    }
    void addcomanda() throws RepositoryException, SQLException {
        System.out.println("Enter id: ");
        String token = choise.next();
        int id = Integer.parseInt(token);
        System.out.println("Enter date (dd/mm/yyyy): ");
        LocalDate date= LocalDate.parse(choise.next(), form);
        System.out.println("Enter the number of cakes: ");
        int n = Integer.parseInt(choise.next());
        ArrayList<tort> torturi = new ArrayList<>();
        for(int i = 0; i< n; i++){
            System.out.println("Cake id: ");
            int idc = Integer.parseInt(choise.next());
            torturi.add(servicetort.getbyId(idc));
        }
        servicecomanda.add(id, date, torturi);
    }
    void updatecomanda() throws RepositoryException, IOException {
        System.out.println("Enter order's id: ");
        String token = choise.next();
        int id = Integer.parseInt(token);
        System.out.println("Enter order's new date (dd/mm/yyyy): ");
        LocalDate date= LocalDate.parse(choise.next(), form);
        System.out.println("Enter the number of cakes: ");
        int n = Integer.parseInt(choise.next());
        ArrayList<tort> torturi = new ArrayList<>();
        for(int i = 0; i< n; i++){
            System.out.println("Cake id: ");
            int idc = Integer.parseInt(choise.next());
            torturi.add(servicetort.getbyId(idc));
        }
        servicecomanda.update(id, date, torturi);
    }
    void deletecomanda() throws RepositoryException {
        System.out.println("Enter id: ");
        String token = choise.next();
        int id = Integer.parseInt(token);
        servicecomanda.delete(id);
    }
    void getAllcomenzi() throws RepositoryException {
        for(int i = 0; i<servicecomanda.getAll().size(); i++){
            System.out.print("Order: " + servicecomanda.getAll().get(i));
        }
    }
    void getcakesbyday() throws RepositoryException {
        ArrayList<TorturiZilnic> list = servicecomanda.countdailycakes();
       for(var tort: list){
           System.out.println(tort);
           System.out.println("\n");
       }
    }
    void getcakesbymonth() throws RepositoryException {
        ArrayList<TorturiLunar> list= servicecomanda.countmonthlycakes();
        for(var tort: list){
            System.out.println(tort);
            System.out.println("\n");
        }
    }
    void getfrequentcakes() throws RepositoryException {
        ArrayList<TorturiFrecvente> list= servicecomanda.frequentcakes();
        for(var tort: list){
            System.out.println(tort);
            System.out.println("\n");
        }
    }

    void options(){
        System.out.println();
        System.out.println("Options: ");
        System.out.println("1. Add cake");
        System.out.println("2. Get All Cakes");
        System.out.println("3. Update cake");
        System.out.println("4. Delete cake");
        System.out.println("5. Add order");
        System.out.println("6. Get All Orders");
        System.out.println("7. Update Order");
        System.out.println("8. Delete Order");
        System.out.println("9. Get the number of cakes for each day");
        System.out.println("10. Get the number of cakes for each month");
        System.out.println("11. Get the number of orders that has each cake");
        System.out.println("0. Exit");
    }
    void menu(){
        Scanner choise = new Scanner(System.in);
        String option = "1";
        while(!option.equals("0")){
            try {
                this.options();
                System.out.println("Option: ");

                option = choise.next();
                switch (option){
                    case "1":
                        this.addtort();
                        break;
                    case "2":
                        this.getAlltorturi();
                        break;
                    case "3":
                        this.updatetort();
                        break;
                    case "4":
                        this.deletetort();
                        break;
                    case "5":
                        this.addcomanda();
                        break;
                    case "6":
                        this.getAllcomenzi();
                        break;
                    case "7":
                        this.updatecomanda();
                        break;
                    case "8":
                        this.deletecomanda();
                        break;
                    case  "9":
                        this.getcakesbyday();
                        break;
                    case "10":
                        this.getcakesbymonth();
                        break;
                    case "11":
                        this.getfrequentcakes();
                        break;
                    case "0":
                        break;
                }
            }catch (RuntimeException | RepositoryException | IOException n) {
                System.out.println(n.toString());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
