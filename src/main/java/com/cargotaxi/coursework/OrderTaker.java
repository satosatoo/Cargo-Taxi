package com.cargotaxi.coursework;

import javafx.scene.control.Alert;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderTaker extends Human {
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

    public static boolean isValidName(String input) {
        // Разделяем строку на имя и фамилию
        String[] nameAndSurname = input.split(" ");

        // Проверяем, что после разделения получены две непустые строки
        if (nameAndSurname.length != 2) {
            return false;
        }

        // Проверяем, что есть хотя бы один пробел между именем и фамилией
        if (!input.contains(" ")) {
            return false;
        }

        String name = nameAndSurname[0];
        String surname = nameAndSurname[1];
        // Проверка регистра
        if (!Character.isUpperCase(name.charAt(0)) || !Character.isUpperCase(surname.charAt(0))) {
            return false;
        }

        // Проверка длины имени и фамилии
        if (name.length() < 2 || name.length() > 50) {
            return false;
        }
        if (surname.length() < 2 || surname.length() > 50) {
            return false;
        }

        // Проверка символов в имени и фамилии
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

    public static boolean isValidAddress(String address) {
        String addressPattern = "^(\\p{L}+),\\s*(\\p{L}+),\\s*(\\d{1,5})$";
        Pattern pattern = Pattern.compile(addressPattern);
        Matcher matcher = pattern.matcher(address);

        if (matcher.matches()) {
            String city = matcher.group(1);
            String street = matcher.group(2);
            String streetNumber = matcher.group(3);

            // Дальнейшая обработка city, street и streetNumber
            // Например, можно добавить дополнительные проверки
            // или возвратить true, если все условия выполняются.

            return true;
        } else {
            return false;
        }
    }

    public static void deleteOrderTaker(int id) {
        orderTakerList.removeIf(orderTaker -> orderTaker.getId() == id);
    }


    public void saveOrderTaker() {
        try {
            FileWriter writer = new FileWriter("orderTakers.txt", true); // 'true' for append mode
            writer.write(this.getId() + " " + this.getFullName() + " " + this.getPhoneNumber() +
                    " " + this.getOfficeAddress() + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readOrderTaker() {
        try {
            File file = new File("orderTakers.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" ");

                if (data.length >= 6) {
                    String id = data[0];
                    String name = data[1] + " " + data[2];
                    String phoneNumber = data[3];
                    String officeAddress = data[4] + " " + data[5];
                    OrderTaker newobj = new OrderTaker(name, phoneNumber, officeAddress);
                    OrderTaker.orderTakerList.add(newobj);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) { e.printStackTrace(); }
    }

    public static OrderTaker findOrderTakerById(int id) {
        for (OrderTaker orderTaker : orderTakerList) {
            if (orderTaker.getId() == id) {
                return orderTaker;
            }
        }
        return null;
    }

    public static void deleteOrderTakerFromFile(int orderTakerId) {
        try {
            File inputFile = new File("orderTakers.txt");
            File tempFile = new File("tempOrderTakers.txt");
            Scanner scanner = new Scanner(inputFile);
            FileWriter writer = new FileWriter(tempFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" ");

                if (data.length >= 1) {
                    int id = Integer.parseInt(data[0]);
                    if (id != orderTakerId) {
                        writer.write(line + "\n");
                    }
                }
            }

            scanner.close();
            writer.close();

            // Замените исходный файл временным файлом
            if (inputFile.delete() && !tempFile.renameTo(inputFile)) {
                System.err.println("Could not rename temp file to original file");
            }
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
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public int getId() { return personalID; }
    public void setOfficeAddress(String officeAddress) { this.officeAddress = officeAddress; }
    public String getOfficeAddress() { return officeAddress; }
}