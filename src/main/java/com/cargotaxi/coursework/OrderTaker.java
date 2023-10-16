package com.cargotaxi.coursework;

import javafx.scene.control.Alert;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderTaker extends Human_Abstract implements Human_Interface {
    public static List<OrderTaker> orderTakerList = new ArrayList<>();
    static int id;
    final private int personalID;
    private String officeAddress;

    OrderTaker(String fullName, String phoneNumber, String officeAddress) {
        super(fullName, phoneNumber);
        this.officeAddress = officeAddress;
        this.personalID = OrderTaker.id++;
    }

    public static void errorName() {
        // Display an error message
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

    public static void errorOfficeAddress() {
        // Display an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Incorrect office address.");

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

    public static boolean isValidName(String name) {
        String regex = "^[A-Za-z\\s\\-]+$";
        return name.matches(regex);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^[0-9\\-\\(\\)\\s]+$";
        return phoneNumber.matches(regex);
    }

    public static boolean isValidAddress(String address) {
        String regex = "^[A-Za-z0-9\\s,\\.]+$";
        return address.matches(regex);
    }

    public static void deleteOrderTaker(int id) {
        orderTakerList.removeIf(orderTaker -> orderTaker.getId() == id);
    }


    public void saveOrderTaker() {
        try {
            FileWriter writer = new FileWriter("orderTakers.txt", true); // 'true' for append mode
            writer.write(this.getId() + "|" + this.getFullName() + "|" + this.getPhoneNumber() + "|" + this.getOfficeAddress() + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isNameAlreadyExists(String fullName) {
        for (OrderTaker orderTaker : orderTakerList) {
            if (orderTaker.getFullName().equalsIgnoreCase(fullName)) {
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

    @Override
    public String showInfo() {
        return ("Id: " + getId() + "  |  Full name: " + getFullName() + "  |  Phone number: " + getPhoneNumber() +
                "  |  Company name: " + getCompanyName() + "  |  Office address: " + getOfficeAddress());
    }

    @Override
    public String getCompanyName() { return companyName; }
    public String getFullName() { return fullName; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getOfficeAddress() { return officeAddress; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setOfficeAddress(String officeAddress) { this.officeAddress = officeAddress; }

    public int getId() { return personalID; }
}