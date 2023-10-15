package com.cargotaxi.coursework;

import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;

public class Cargo {
    public static List<Cargo> cargoList = new ArrayList<>();
    static int id;
    final int cargoID;
    private String cargoName;
    private String cargoPuckUp;
    private String cargoDropOff;
    private double price;
    private double weight;
    private boolean cargoStatus;
    private static double pricePerKg = 2.5;

    Cargo(String cargoName, String cargoFrom, String cargoTo, String weight, String price) {
        this.cargoName = cargoName;
        this.cargoPuckUp = cargoFrom;
        this.cargoDropOff = cargoTo;
        this.weight = Double.parseDouble(weight);
        this.price = Double.parseDouble(price);
        this.cargoID = Cargo.id++;
        this.cargoStatus = false;
        cargoPrice();
    }

    public static void errorCargoName() {
        // Display an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null); // You can set a custom header text if needed
        alert.setContentText("Incorrect cargo name.");

        alert.showAndWait(); // Show the dialog and wait for the user to close it
    }

    public static void errorPickUp() {
        // Display an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null); // You can set a custom header text if needed
        alert.setContentText("Incorrect pick up location.");

        alert.showAndWait(); // Show the dialog and wait for the user to close it
    }

    public static void errorDropOff() {
        // Display an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null); // You can set a custom header text if needed
        alert.setContentText("Incorrect drop off location.");

        alert.showAndWait(); // Show the dialog and wait for the user to close it
    }

    public static void errorWeight() {
        // Display an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null); // You can set a custom header text if needed
        alert.setContentText("Wrong weight.");

        alert.showAndWait(); // Show the dialog and wait for the user to close it
    }

    public static void errorNothingEntered() {
        // Display an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null); // You can set a custom header text if needed
        alert.setContentText("Fill in at least one line.");

        alert.showAndWait(); // Show the dialog and wait for the user to close it
    }

    // Simple name validation using a regular expression
    public static boolean isValidCargoName(String name) {
        // This regex allows letters, spaces, and hyphens, but you can adjust it as needed
        String regex = "^[A-Za-z\\s\\-]+$";
        return name.matches(regex);
    }

    // Simple phone number validation using a regular expression
    public static boolean isValidPickupAndDropOffLocation(String location) {
        // Customize this regex to match your specific validation criteria
        String regex = "^[A-Za-z0-9\\s,\\.\\-]+$";
        return location.matches(regex);
    }

    // Simple weight validation
    public static boolean isValidWeight(String weight) {
        try {
            double parsedWeight = Double.parseDouble(weight);
            // Customize this range to match your specific validation criteria
            return parsedWeight >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

                // Когда у водителя обновляется статус на свободен то вызывается этот метод и статус груза становится "Delivered"
    public void cargoDeliveredById(ArrayList<Cargo> array, int id) {
        for (Cargo obj : array) {
            if (obj.getCargoId() == id) {
                obj.setCargoStatusOnTheWay();
            }
        }
    }

    public void deleteCargoById(ArrayList<Cargo> array, int id) {
        for (Cargo obj : array) {
            if (obj.getCargoId() == id) {
                obj = null;
            }
        }
    }

    public double cargoPrice() {
        setPrice(getWeight() * getPricePerKg());
        return getPrice();
    }

    public String showInfo() {
        return ("Cargo name: " + getCargoName() + " |~| Place of departure: " + getCargoPuckUp() +
                " |~| Arrival place: " + getCargoDropOff() + " |~| Weight: " + getWeight() +
                " |~| Cargo status: " + getCargoStatusText() + " |~| Price: " + getPrice() +
                " |~| Id: " + getCargoId());
    }
    
    // Getters and setters
    public int getCargoId() { return cargoID; }
    public String getCargoName() { return cargoName; }
    public String getCargoPuckUp() { return cargoPuckUp; }
    public String getCargoDropOff() { return cargoDropOff; }
    public double getPrice() { return price; }
    public double getWeight() { return weight; }

    public void setCargoName(String cargoName) { this.cargoName = cargoName; }
    public void setCargoPuckUp(String cargoFrom) { this.cargoPuckUp = cargoFrom; }
    public void setCargoDropOff(String cargoTo) { this.cargoDropOff = cargoTo; }
    public void setPrice(double price) { this.price = price; }
    public void setWeight(double weight) { this.weight = weight; }

    public String getCargoStatusText() {
        if (cargoStatus) { return "on the way"; }
        else { return "not processed"; }
    }
    public boolean getCargoStatusBoolean() {
        return this.cargoStatus;
    }
    public void setCargoStatusOnTheWay() { this.cargoStatus = true; }
    public static double getPricePerKg() { return pricePerKg; }
}