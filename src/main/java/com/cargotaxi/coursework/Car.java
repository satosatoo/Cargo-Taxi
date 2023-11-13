package com.cargotaxi.coursework;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Car {
    private String carNumber = null;
    private final static double limitWeight = 1000;
    private String carModel = null;

    private static List<String> usedCarNumbers = new ArrayList<>();

    public static String createUniqueCarNumber() {
        Random random = new Random();
        int min = 100000;
        int max = 999999;

        String result = "";
        boolean unique = false;

        while (!unique) {
            int randomNumber = random.nextInt(max - min + 1) + min;
            result = String.valueOf(randomNumber);

            // Проверьте, что номер машины уникален
            if (!usedCarNumbers.contains(result)) {
                unique = true;
                usedCarNumbers.add(result);
            }
        }

        return result;
    }

    public String getCarNumber() { return carNumber; }
    public void setCarNumber(String carNumber) { this.carNumber = carNumber; }
    public String getCarModel() { return carModel; }
    public void setCarModel(String carModel) { this.carModel = carModel; }
    public static double getLimit() { return limitWeight; }
}