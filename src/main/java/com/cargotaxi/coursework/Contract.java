package com.cargotaxi.coursework;

import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Contract {
    static int id;
    public final int contractId;
    LocalDate appointment;
    LocalDate deliveryDate;
    Cargo cargoC;
    OrderTaker orderTakerC;
    Driver driverC;

    public static List<Contract> contractList = new ArrayList<>();

    Contract(Cargo cargo, OrderTaker orderTaker, Driver driver, LocalDate appointment, LocalDate deliveryDate) {        // ВОЗМОЖНО ДРУГОЙ АТРИБУТ
        this.cargoC = cargo;
        this.orderTakerC = orderTaker;
        this.driverC = driver;
        driverC.setInfoFromContract(cargoC.getCargoId(), cargoC.getWeight());
        this.appointment = appointment;
        this.deliveryDate = deliveryDate;
        this.contractId = Contract.id++;
    }



    public static void errorCargo() {
        // Display an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null); // You can set a custom header text if needed
        alert.setContentText("To create you must select Cargo.");

        alert.showAndWait(); // Show the dialog and wait for the user to close it
    }

    public static void errorOrderTaker() {
        // Display an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null); // You can set a custom header text if needed
        alert.setContentText("to create you must select Order Taker.");

        alert.showAndWait(); // Show the dialog and wait for the user to close it
    }

    public static void errorDriver() {
        // Display an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null); // You can set a custom header text if needed
        alert.setContentText("To create you must select Driver.");

        alert.showAndWait(); // Show the dialog and wait for the user to close it
    }

    public static void errorDate() {
        // Display an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null); // You can set a custom header text if needed
        alert.setContentText("To create you must select an appointment date.");

        alert.showAndWait(); // Show the dialog and wait for the user to close it
    }

    public int getContractId() { return this.contractId; }

    public String showInfo() {
        return ("Contract id: " + getContractId() + " |~| Delivery date: " + getAppointment() + " |~| Driver fullname: " + driverC.getFullName() +
        " |~| Driver id: " + driverC.getId() + " |~| Order receiver fullname: " + orderTakerC.getFullName() +
        " |~| Order receiver id: " + orderTakerC.getId() + " |~| Cargo name: " + cargoC.getCargoName() + " |~| Cargo id: " + cargoC.getCargoId());
    }

    public LocalDate getAppointment() { return this.appointment; }
    public void setAppointment(LocalDate date) { this.appointment = date; }
}