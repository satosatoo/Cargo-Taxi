package com.cargotaxi.coursework;

import java.util.Random;

public class Truck {
    private String truckNumber;
    private final double limitWeight = 1000;

    Truck() {
        this.truckNumber = createTruckNumber();
    }

    private String createTruckNumber() {
        Random random = new Random();
        int min = 100000;
        int max = 999999;
        
        int randomNumber = random.nextInt(max - min + 1) + min;
        String result = "" + randomNumber;
        return result;
    }

    // Getters and setters
    public String getTruckNumber() { return truckNumber; }
    public double getLimit() { return limitWeight; }
    public void setTruckNumber(String truckNumber) { this.truckNumber = truckNumber; }
}