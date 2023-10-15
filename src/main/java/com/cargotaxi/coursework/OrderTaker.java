package com.cargotaxi.coursework;

import javafx.scene.control.Alert;
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
        alert.setHeaderText(null); // You can set a custom header text if needed
        alert.setContentText("Incorrect full name.");

        alert.showAndWait(); // Show the dialog and wait for the user to close it
    }

    public static void errorPhoneNumber() {
        // Display an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null); // You can set a custom header text if needed
        alert.setContentText("Wrong phone number.");

        alert.showAndWait(); // Show the dialog and wait for the user to close it
    }

    public static void errorOfficeAddress() {
        // Display an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null); // You can set a custom header text if needed
        alert.setContentText("Incorrect office address.");

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
    public static boolean isValidName(String name) {
        // This regex allows letters, spaces, and hyphens, but you can adjust it as needed
        String regex = "^[A-Za-z\\s\\-]+$";
        return name.matches(regex);
    }

    // Simple phone number validation using a regular expression
    public static boolean isValidPhoneNumber(String phoneNumber) {
        // This regex allows digits and optional hyphens, parentheses, and spaces, but you can adjust it as needed
        String regex = "^[0-9\\-\\(\\)\\s]+$";
        return phoneNumber.matches(regex);
    }

    // Simple address validation using a regular expression
    public static boolean isValidAddress(String address) {
        // This regex allows letters, digits, spaces, commas, and periods, but you can adjust it as needed
        String regex = "^[A-Za-z0-9\\s,\\.]+$";
        return address.matches(regex);
    }

    public static void deleteOrderTaker(int id) {
        orderTakerList.removeIf(orderTaker -> orderTaker.getId() == id);
    }

    // Overrided
    @Override
    public String showInfo() {
        return ("Id: " + getId() + "  |  Full name: " + getFullName() + "  |  Phone number: " + getPhoneNumber() +
                "  |  Company name: " + getCompanyName() + "  |  Office address: " + getOfficeAddress());
    }

    // Getters and setters
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