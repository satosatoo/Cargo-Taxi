package com.cargotaxi.coursework;

import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Contract {
    static int id = 1;
    public final int contractID;
    Cargo cargoC;
    OrderTaker orderTakerC;
    Driver driverC;

    public static List<Contract> contractList = new ArrayList<>();

    Contract(Cargo cargo, OrderTaker orderTaker, Driver driver) {        // ВОЗМОЖНО ДРУГОЙ АТРИБУТ
        this.cargoC = cargo;
        this.orderTakerC = orderTaker;
        this.driverC = driver;
        if (driverC != null) {
            driverC.setInfoFromContract(cargoC.getCargoId(), cargoC.getWeight());
        }
        this.contractID = Contract.id++;
    }

    public static void errorCargo() {
        // Display an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("To create you must select Cargo.");

        alert.showAndWait();
    }

    public static void errorOrderTaker() {
        // Display an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("to create you must select Order Taker.");

        alert.showAndWait();
    }

    public static void errorDriver() {
        // Display an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("To create you must select Driver.");

        alert.showAndWait();
    }

    public void saveContract() {
        try {
            FileWriter writer = new FileWriter("contracts.txt", true); // 'true' for append mode
            writer.write(this.getContractId() + " " + this.cargoC.getCargoId() + " " + this.driverC.getId() +
                    " " + this.orderTakerC.getId() + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void rewriteContractFile() {
        try {
            FileWriter writer = new FileWriter("contracts.txt", false); // 'false' to overwrite the file
            for (Contract contract : contractList) {
                writer.write(contract.getContractId() + " " + contract.cargoC.getCargoId() +
                        " " + contract.driverC.getId() + " " + contract.orderTakerC.getId() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readContract() {
        try {
            File file = new File("contracts.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" ");

                if (data.length >= 4) {
                    String id = data[0];
                    String cargoID = data[1];
                    String driverID = data[2];
                    String orderTakerID = data[3];
                    Contract newobj = new Contract(Cargo.findCargoById(Integer.parseInt(cargoID)),
                            OrderTaker.findOrderTakerById(Integer.parseInt(orderTakerID)),
                            Driver.findDriverById(Integer.parseInt(driverID)));
                    Contract.contractList.add(newobj);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) { e.printStackTrace(); }
    }

    public static Contract findContractById(int id) {
        for (Contract contract : contractList) {
            if (contract.getID() == id) {
                return contract;
            }
        }
        return null;
    }

    public String showInfo() {
        return ("Contract ID: " + getContractId() + "  |  Driver ID: " + driverC.getId() +
                "  |  Driver name: " + driverC.getFullName() + "  |  Order taker ID: " + orderTakerC.getId() +
                "  |  Order taker name: " + orderTakerC.getFullName() + "  |  Cargo ID: " + cargoC.getCargoId() +
                "  |  Cargo name: " + cargoC.getCargoName());
    }

    public int getContractId() { return this.contractID; }
    public int getID() { return this.contractID; }
}