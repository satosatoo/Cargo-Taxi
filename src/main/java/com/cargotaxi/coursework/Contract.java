package com.cargotaxi.coursework;

import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Contract {
    static int id;
    public final int contractID;
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
        this.contractID = Contract.id++;
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

    public int getContractId() { return this.contractID; }

    public String showInfo() {
        return ("Contract ID: " + getContractId() + "  |  Appointment: " + getAppointment() +
                "  |  Delivery date: " + getDeliveryDate() + "  |  Driver ID: " + driverC.getId() +
                "  |  Order receiver ID: " + orderTakerC.getId() + "  |  Cargo ID: " + cargoC.getCargoId());
    }

    public LocalDate getAppointment() { return this.appointment; }
    public void setAppointment(LocalDate date) { this.appointment = date; }
    public LocalDate getDeliveryDate() { return this.deliveryDate; }
    public void setDeliveryDate(LocalDate deliveryDate) { this.deliveryDate = deliveryDate; }
}