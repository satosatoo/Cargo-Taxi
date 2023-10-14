package com.cargotaxi.coursework;

import java.util.ArrayList;
import java.util.List;

public class Cargo {
    public static List<Cargo> cargoList = new ArrayList<>();
    static int id;
    private int cargoId;
    private String cargoName;
    private String cargoFrom;
    private String cargoTo;
    private double price;
    private double weight;
    private boolean cargoStatus;
    private int pricePerKg = 2;

    Cargo(String cargoName, String cargoFrom, String cargoTo, double weight) {
        this.cargoName = cargoName;
        this.cargoFrom = cargoFrom;
        this.cargoTo = cargoTo;
        this.weight = weight;
        this.cargoId = OrderReceiver.id++;
        this.cargoStatus = false;
        cargoPrice();
    }

    public void createCargo(String cargoName, String cargoFrom, String cargoTo, double weight) {
        Cargo cargo = new Cargo(cargoName, cargoFrom, cargoTo, weight);
        Cargo.cargoList.add(cargo);
    }

                // Когда у водителя обновляется статус на свободен то вызывается этот метод и статус груза становится "Delivered"
    public void cargoDeliveredById(ArrayList<Cargo> array, int id) {
        for (Cargo obj : array) {
            if (obj.getCargoId() == id) {
                obj.setCargoStatusDelivered();
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

    public void cargoPrice() {
        setPrice(getWeight() * getPricePerKg());
    }

    // Нужно добавить сюда printInfo
    public void showInfo() {
        System.out.println("Cargo name: " + getCargoName() + " |~| Place of departure: " + getCargoFrom() + " |~| Arrival place: " + getCargoTo() + 
        " |~| Weight: " + getWeight() + " |~| Cargo status: " + getCargoStatusText() + " |~| Id: " + getCargoId());
    }
    
    // Getters and setters
    public int getCargoId() { return cargoId; }
    public String getCargoName() { return cargoName; }
    public String getCargoFrom() { return cargoFrom; }
    public String getCargoTo() { return cargoTo; }
    public double getPrice() { return price; }
    public double getWeight() { return weight; }

    public void setCargoId(int cargoId) { this.cargoId = cargoId; }
    public void setCargoName(String cargoName) { this.cargoName = cargoName; }
    public void setCargoFrom(String cargoFrom) { this.cargoFrom = cargoFrom; }
    public void setCargoTo(String cargoTo) { this.cargoTo = cargoTo; }
    public void setPrice(double price) { this.price = price; }
    public void setWeight(double weight) { this.weight = weight; }

    public String getCargoStatusText() {
        if (cargoStatus) { return "delivered"; }
        else { return "in progress"; }
    }
    public boolean getCargoStatusBoolean() {
        return this.cargoStatus;
    }
    public void setCargoStatusDelivered() { this.cargoStatus = true; }
    public int getPricePerKg() { return this.pricePerKg; }
}