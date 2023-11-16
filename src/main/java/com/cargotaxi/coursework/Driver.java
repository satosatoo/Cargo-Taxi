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

public class Driver extends Human {
    public static List<Driver> driverList = new ArrayList<>();
    static int id = 1;
    final int personalID;
    private boolean driverStatus = true;
    private int cargoIdForDriver;
    private double cargoWeightForDriver;
    Car car = new Car();

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
        alert.setContentText("Incorrect full name. The first and last name must be capitalized and at least 2 letters.");
        alert.showAndWait();
    }

    public static void errorPhoneNumber() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Wrong phone number. The phone number must start with 380 or +380 and be 12 digits long.");
        alert.showAndWait();
    }

    public static void errorNothingEntered() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Fill in at least one line.");
        alert.showAndWait();
    }

    public static void errorChooseCarModel() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("You need to select a car model.");
        alert.showAndWait();
    }

    public static void errorDeleteDriver() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("You cannot remove a driver if he is busy.");
        alert.showAndWait();
    }

    public static boolean isValidName(String input) {
        String[] nameAndSurname = input.split(" ");

        if (nameAndSurname.length != 2) {
            return false;
        }

        if (!input.contains(" ")) {
            return false;
        }

        String name = nameAndSurname[0];
        String surname = nameAndSurname[1];
        // Проверка регистра
        if (!Character.isUpperCase(name.charAt(0)) || !Character.isUpperCase(surname.charAt(0))) {
            return false;
        }

        if (name.length() < 2 || name.length() > 50) {
            return false;
        }
        if (surname.length() < 2 || surname.length() > 50) {
            return false;
        }

        if (!name.matches("^[a-zA-Z'-]*$") || !surname.matches("^[a-zA-Z'-]*$")) {
            return false;
        }

        return true;
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^(\\+380|380)[0-9\\-\\(\\)\\s]*[0-9\\-\\(\\)\\s]+$";

        int minPhoneNumberLength = 12; // Минимальная длина номера
        int maxPhoneNumberLength = 13; // Максимальная длина номера

        if (phoneNumber != null && phoneNumber.matches(regex)) {
            return phoneNumber.length() == minPhoneNumberLength || phoneNumber.length() == maxPhoneNumberLength;
        }
        return false;
    }

    public void setInfoFromContract(int cargoId, double cargoWeight) { 
        setCargoIdForDriver(cargoId);
        setCargoWeightForDriver(cargoWeight);
    }

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
        for (OrderTaker orderTaker : OrderTaker.orderTakerList) {
            if (orderTaker.getFullName().equalsIgnoreCase(fullName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPhoneNumberAlreadyExists(String phoneNumber) {
        for (Driver driver : driverList) {
            if (driver.getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
                return true;
            }
        }
        for (OrderTaker orderTaker : OrderTaker.orderTakerList) {
            if (orderTaker.getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

    public static void errorPhoneNumberExists() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("This phone number already exists. Please enter a different phone number.");
        alert.showAndWait();
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
            writer.write(this.getId() + " " + this.getFullName() + " " + this.getPhoneNumber() +
                    " " + car.getCarModel() + " " + car.getCarNumber() + " " + this.getDriverStatusBoolean() + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void rewriteDriverFile() {
        try {
            FileWriter writer = new FileWriter("drivers.txt", false); // 'false' to overwrite the file
            for (Driver driver : driverList) {
                writer.write(driver.getId() + " " + driver.getFullName() + " " + driver.getPhoneNumber() +
                        " " + driver.car.getCarModel() + " " + driver.car.getCarNumber() + " " + driver.getDriverStatusBoolean() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readDriver() {
        try {
            File file = new File("drivers.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" ");

                if (data.length >= 7) {
                    String id = data[0];
                    String name = data[1] + " " + data[2];
                    String phoneNumber = data[3];
                    String carModel = data[4];
                    String carNumber = data[5];
                    String status = data[6];
                    Driver newobj = new Driver(name, phoneNumber, carModel, carNumber);
                    newobj.setDriverStatus(Boolean.parseBoolean(status));
                    Driver.driverList.add(newobj);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) { e.printStackTrace(); }
    }

    public static void deleteDriver(int id) {
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

    public static Driver findDriverById(int id) {
        for (Driver driver : driverList) {
            if (driver.getId() == id) {
                return driver;
            }
        }
        return null;
    }

    public static void deleteDriverFromFile(int driverId) {
        try {
            File inputFile = new File("drivers.txt");
            File tempFile = new File("tempDrivers.txt");
            Scanner scanner = new Scanner(inputFile);
            FileWriter writer = new FileWriter(tempFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" ");

                if (data.length >= 1) {
                    int id = Integer.parseInt(data[0]);
                    if (id != driverId) {
                        writer.write(line + "\n");
                    }
                }
            }

            scanner.close();
            writer.close();

            if (inputFile.delete() && !tempFile.renameTo(inputFile)) {
                System.err.println("Could not rename temp file to original file");
            }
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
    public void setDriverStatus(boolean boo) { this.driverStatus = boo; }
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