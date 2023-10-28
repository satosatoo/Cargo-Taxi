package com.cargotaxi.coursework;

import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cargo {
    public static List<Cargo> cargoList = new ArrayList<>();
    static int id;
    private final int cargoID;
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
        try {
            this.weight = Double.parseDouble(weight);
        } catch (NumberFormatException e) {
        }
        this.cargoID = Cargo.id++;
        this.price = Double.parseDouble(price);
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

    public static void errorIdenticalPickUpAndDropOff() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Pick Up and Drop Off locations cannot be identical.");

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
        String regex = "^[\\p{L}0-9,\\s.'-]+,\\s*\\d{1,5}$";
        return location.matches(regex);
    }

    // Simple weight validation
    public static boolean isValidWeight(String weight) {
        try {
            double parsedWeight = Double.parseDouble(weight);
            return parsedWeight >= 100;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void saveCargo() {
        try {
            FileWriter writer = new FileWriter("cargoes.txt", true); // 'true' for append mode
            writer.write(this.getCargoId() + " " + this.getCargoName() + " " + this.getCargoPickUp() +
                    " " + this.getCargoDropOff() + " " + this.getWeight() + " " + this.getPrice() +
                    " " + this.getCargoStatusBoolean() + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readCargo() {
        try {
            File file = new File("cargoes.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" ");

                if (data.length >= 9) {
                    String id = data[0];
                    String name = data[1];
                    String pickUp = data[2] + " " + data[3];
                    String dropOff = data[4] + " " + data[5];
                    String weight = data[6];
                    String price = data[7];
                    String status = data[8];
                    Cargo newobj = new Cargo(name, pickUp, dropOff, weight, price);
                    newobj.setCargoStatus(Boolean.parseBoolean(status));
                    Cargo.cargoList.add(newobj);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) { e.printStackTrace(); }
    }

    public static Cargo findCargoById(int id) {
        for (Cargo cargo : cargoList) {
            if (cargo.getCargoId() == id) {
                return cargo;
            }
        }
        return null;
    }

    public double cargoPrice() {
        setPrice(getWeight() * getPricePerKg());
        return getPrice();
    }

    public String showInfo() {
        return ("Id: " + getCargoId() + " |~| Cargo name: " + getCargoName() + " |~| Place of departure: " + getCargoPickUp() +
                " |~| Arrival place: " + getCargoDropOff() + " |~| Weight: " + getWeight() +
                " |~| Cargo status: " + getCargoStatusText() + " |~| Price: " + getPrice());
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
    public void setCargoStatus(boolean boo) { this.cargoStatus = boo; }
    public static double getPricePerKg() { return pricePerKg; }
}