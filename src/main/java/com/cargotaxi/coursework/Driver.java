package com.cargotaxi.coursework;

import javafx.scene.control.Alert;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Driver extends Human_Abstract implements Human_Interface {
    public static List<Driver> driverList = new ArrayList<>();
    static int id;
    final int personalID;
    private boolean driverStatus = true;
    private int cargoIdForDriver;
    private double cargoWeightForDriver;
    static Car car = new Car();

    Driver(String fullName, String phoneNumber, String carModel, String carNumber) {
        super(fullName, phoneNumber);
        car.setCarModel(carModel);
        car.setCarNumber(carNumber);
        this.personalID = Driver.id++;
    }

    public static void errorName() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Incorrect full name.");

        alert.showAndWait();
    }

    public static void errorPhoneNumber() {
        // Display an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Wrong phone number.");

        alert.showAndWait();
    }

    public static void errorNothingEntered() {
        // Display an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Fill in at least one line.");

        alert.showAndWait();
    }

    public static void errorChooseCarModel() {
        // Display an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("You need to select a car model.");

        alert.showAndWait();
    }

    public static boolean isValidName(String name) {
        // This regex allows letters, spaces, and hyphens, but you can adjust it as needed
        String regex = "^[A-Za-z\\s\\-]+$";
        return name.matches(regex);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^[0-9\\-\\(\\)\\s]+$";
        return phoneNumber.matches(regex);
    }

    public static void deleteDriver(int id) {
        // Найдите водителя по id
        Driver driverToRemove = null;
        for (Driver driver : driverList) {
            if (driver.getId() == id) {
                driverToRemove = driver;
                break;
            }
        }

        if (driverToRemove != null) {
            if (driverToRemove.getDriverStatusBoolean()) {
                driverList.remove(driverToRemove);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("The driver is currently busy and cannot be deleted.");
                alert.showAndWait();
            }
        }
    }

    public void setInfoFromContract(int cargoId, double cargoWeight) { 
        setCargoIdForDriver(cargoId);
        setCargoWeightForDriver(cargoWeight);
    }
    public void ifDriverFreeThanNoCargoId() { this.cargoIdForDriver = 0; }

    public static LocalDate deliveryTime(LocalDate date, double weight, double limit) {
        double calc = weight / limit;
        double days = Math.ceil(calc);
        return date.plusDays(Math.round(days));
    }

    public static boolean isNameAlreadyExists(String fullName) {
        for (Driver driver : driverList) {
            if (driver.getFullName().equalsIgnoreCase(fullName)) {
                return true;
            }
        }
        return false;
    }

    public static void errorNameExists() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("This name already exists. Please enter a different name.");
        alert.showAndWait();
    }

    public void saveDriver() {
        try {
            FileWriter writer = new FileWriter("drivers.txt", true); // 'true' for append mode
            writer.write(this.getId() + "|" + this.getFullName() + "|" + this.getPhoneNumber() + "|" + car.getCarModel() + "|" + car.getCarNumber() + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String showInfo() {
        return ("Id: " + getId() + "  |  Full name: " + getFullName() + "  |  Phone number: " + getPhoneNumber() +
                "  |  Company name: " + getCompanyName() + "  |  Model of car : " + car.getCarModel() +
                "  |  Number of the car : " + car.getCarNumber() + "  |  Driver status : " + getDriverStatusText());
    }

    public void setCargoIdForDriver(int id) { this.cargoIdForDriver = id; }
    public int getCargoIdForDriver() { return this.cargoIdForDriver; }
    public int getId() { return personalID; }
    public void setDriverStatusBusy() { this.driverStatus = false; }
    public boolean getDriverStatusBoolean() { return this.driverStatus; }
    public String getDriverStatusText() {
        if (driverStatus) { return "free"; }
        else { return "busy"; }
    }
    public void setCargoWeightForDriver(double weight) { this.cargoWeightForDriver = weight; }
    public double getCargoWeightForDriver() { return this.cargoWeightForDriver; }
    public double getLimitWeight() {
        return car.getLimit();
    }

    @Override
    public String getCompanyName() { return companyName; }
    public String getFullName() { return fullName; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}