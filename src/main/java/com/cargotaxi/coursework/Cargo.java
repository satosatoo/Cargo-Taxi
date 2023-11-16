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

public class Cargo {
    public static List<Cargo> cargoList = new ArrayList<>();
    static int id = 1;
    private final int cargoID;
    private String cargoName;
    private String cargoPickUp;
    private String cargoDropOff;
    private double price;
    private double weight;
    private boolean cargoStatus = true;
    LocalDate appointment;
    LocalDate deliveryDate;
    private static double pricePerKg = 2.5;

    Cargo(String cargoName, String cargoFrom, String cargoTo, String weight, String price, LocalDate appointment, LocalDate deliveryDate) {
        this.cargoName = cargoName;
        this.cargoPickUp = cargoFrom;
        this.cargoDropOff = cargoTo;
        try {
            this.weight = Double.parseDouble(weight);
        } catch (NumberFormatException e) {
        }
        this.cargoID = Cargo.id++;
        this.price = Double.parseDouble(price);
        this.appointment = appointment;
        this.deliveryDate = deliveryDate;
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
        alert.setContentText("Incorrect pick up location. There must first be the name of the city, then the name of the street and then the street number, everything must be separated by a comma.");

        alert.showAndWait();
    }

    public static void errorDropOff() {
        // Display an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Incorrect drop off location. There must first be the name of the city, then the name of the street and then the street number, everything must be separated by a comma.");

        alert.showAndWait();
    }

    public static void errorWeight() {
        // Display an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Wrong weight. The weight of the cargo must be at least 100 kg.");

        alert.showAndWait();
    }

    public static void errorIdenticalPickUpAndDropOff() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Pick Up and Drop Off locations cannot be identical.");

        alert.showAndWait();
    }

    public static void errorDate() {
        // Display an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("To create you must select an appointment date.");

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
                    " " + this.getCargoStatusBoolean() + " " + this.getAppointment() + " " + this.getDeliveryDate() + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void rewriteCargoFile() {
        try {
            FileWriter writer = new FileWriter("cargoes.txt", false); // 'false' to overwrite the file
            for (Cargo cargo : cargoList) {
                writer.write(cargo.getCargoId() + " " + cargo.getCargoName() + " " + cargo.getCargoPickUp() +
                        " " + cargo.getCargoDropOff() + " " + cargo.getWeight() + " " + cargo.getPrice() +
                        " " + cargo.getCargoStatusBoolean() + " " + cargo.getAppointment() + " " + cargo.getDeliveryDate() + "\n");
            }
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

                if (data.length >= 13) {
                    String id = data[0];
                    String name = data[1];
                    String pickUp = data[2] + " " + data[3] + " " + data[4];
                    String dropOff = data[5] + " " + data[6] + " " + data[7];
                    String weight = data[8];
                    String price = data[9];
                    String status = data[10];
                    LocalDate appointment = LocalDate.parse(data[11]);
                    LocalDate deliveryDate = LocalDate.parse(data[12]);
                    Cargo newobj = new Cargo(name, pickUp, dropOff, weight, price, appointment, deliveryDate);
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
        return ("Id: " + getCargoId() + "  |  Cargo name: " + getCargoName() + "  |  Place of departure: " + getCargoPickUp() +
                "  |  Arrival place: " + getCargoDropOff() + "  |  Weight: " + getWeight() + "kg." +
                "  |  Cargo status: " + getCargoStatusText() + "  |  Price: " + getPrice() + "$" +
                "  |  Appointment: " + getAppointment() + "  |  Delivery date: " + getDeliveryDate());
    }

    public static List<String> getYearsFromData() {
        List<String> years = new ArrayList<>();
        for (Cargo cargo : Cargo.cargoList) {
            String year = String.valueOf(cargo.getAppointment().getYear());
            if (!years.contains(year)) {
                years.add(year);
            }
        }
        return years;
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
    public LocalDate getAppointment() { return this.appointment; }
    public void setAppointment(LocalDate date) { this.appointment = date; }
    public LocalDate getDeliveryDate() { return this.deliveryDate; }
    public void setDeliveryDate(LocalDate deliveryDate) { this.deliveryDate = deliveryDate; }
}