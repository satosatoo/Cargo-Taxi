package com.cargotaxi.coursework;

import javafx.scene.control.Alert;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cargo {
    public static List<Cargo> cargoList = new ArrayList<>();
    static int id;
    final int cargoID;
    private String cargoName;
    private String cargoPickUp;
    private String cargoDropOff;
    private double price;
    private double weight;
    private boolean cargoStatus = true;
    private static double pricePerKg = 2.5;

    Cargo(String cargoName, String cargoFrom, String cargoTo, String weight, String price) {
        this.cargoName = cargoName;
        this.cargoPickUp = cargoFrom;
        this.cargoDropOff = cargoTo;
        this.weight = Double.parseDouble(weight);
        this.price = Double.parseDouble(price);
        this.cargoID = Cargo.id++;
        cargoPrice();
    }

    public static void errorCargoName() {
        // Display an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Incorrect cargo name.");

        alert.showAndWait();
    }

    public static void errorPickUp() {
        // Display an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Incorrect pick up location.");

        alert.showAndWait();
    }

    public static void errorDropOff() {
        // Display an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Incorrect drop off location.");

        alert.showAndWait();
    }

    public static void errorWeight() {
        // Display an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Wrong weight.");

        alert.showAndWait();
    }

    public static void errorNothingEntered() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Fill in at least one line.");

        alert.showAndWait();
    }

    public static boolean isValidCargoName(String name) {
        String regex = "^[A-Za-z\\s\\-]+$";
        return name.matches(regex);
    }

    public static boolean isValidPickupAndDropOffLocation(String location) {
        String regex = "^[A-Za-z0-9\\s,\\.\\-]+$";
        return location.matches(regex);
    }

    // Simple weight validation
    public static boolean isValidWeight(String weight) {
        try {
            double parsedWeight = Double.parseDouble(weight);
            return parsedWeight >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void saveCargo() {
        try {
            FileWriter writer = new FileWriter("cargoes.txt", true); // 'true' for append mode
            writer.write(this.getCargoId() + "|" + this.getCargoName() + "|" + this.getCargoPickUp() +
                    "|" + this.getCargoDropOff() + "|" + this.getWeight() + "|" + this.getPrice() + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double cargoPrice() {
        setPrice(getWeight() * getPricePerKg());
        return getPrice();
    }

    public String showInfo() {
        return ("Cargo name: " + getCargoName() + " |~| Place of departure: " + getCargoPickUp() +
                " |~| Arrival place: " + getCargoDropOff() + " |~| Weight: " + getWeight() +
                " |~| Cargo status: " + getCargoStatusText() + " |~| Price: " + getPrice() +
                " |~| Id: " + getCargoId());
    }
    
    // Getters and setters
    public int getCargoId() { return cargoID; }
    public String getCargoName() { return cargoName; }
    public String getCargoPickUp() { return cargoPickUp; }
    public String getCargoDropOff() { return cargoDropOff; }
    public double getPrice() { return price; }
    public double getWeight() { return weight; }
    public void setCargoName(String cargoName) { this.cargoName = cargoName; }
    public void setCargoPickUp(String cargoFrom) { this.cargoPickUp = cargoFrom; }
    public void setCargoDropOff(String cargoTo) { this.cargoDropOff = cargoTo; }
    public void setPrice(double price) { this.price = price; }
    public void setWeight(double weight) { this.weight = weight; }
    public String getCargoStatusText() {
        if (cargoStatus) { return "not processed"; }
        else { return "on the way"; }
    }
    public boolean getCargoStatusBoolean() {
        return this.cargoStatus;
    }
    public void setCargoStatusOnTheWay() { this.cargoStatus = false; }
    public static double getPricePerKg() { return pricePerKg; }
}