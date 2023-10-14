package com.cargotaxi.coursework;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Driver extends Human_Abstract implements Human_Interface {
    public static List<Driver> driverList = new ArrayList<>();
    static int id;
    final private int personalId;
    private boolean driverStatus;
    private int cargoIdForDriver;
    private double cargoWeightForDriver;
    Truck truck = new Truck();

    Driver(String fullName, String phoneNumber) {
        super(fullName, phoneNumber);
        this.personalId = OrderTaker.id++;
        setDriverStatusBoolean(true);
    }

    public void createDriver(String fullName, String phoneNumber) {
        Driver driver = new Driver(fullName, phoneNumber);
        Driver.driverList.add(driver);
    }

    public void deleteDriverById(ArrayList<Driver> array, int id) {
        for (Driver obj : array) {
            if (obj.getId() == id) {         // zakinut' eto v class Driver
                if (obj.getDriverStatusBoolean()) {
                    obj = null;
                } else {
                    break;
                }
            }
        }
    }

    public void setCargoIdForDriver(int id) { this.cargoIdForDriver = id; }
    public int getCargoIdForDriver() { return this.cargoIdForDriver; }

    public void setInfoFromContract(int cargoId, double cargoWeight) { 
        setCargoIdForDriver(cargoId);
        setCargoWeightForDriver(cargoWeight);
        setDriverStatusBoolean(false);
    }
    public void ifDriverFreeThanNoCargoId() { this.cargoIdForDriver = 0; }

    public int getId() { return personalId; }
    
    public void setDriverStatusBoolean(boolean boo) { this.driverStatus = boo; }
    public boolean getDriverStatusBoolean() { return this.driverStatus; }
    public String getDriverStatusText() {
        if (driverStatus) { return "free"; }
        else { return "busy"; }
    }

    public void setCargoWeightForDriver(double weight) { this.cargoWeightForDriver = weight; }
    public double getCargoWeightForDriver() { return this.cargoWeightForDriver; }

    public double getLimitWeight() {
        return truck.getLimit();
    }

    public LocalDate deliveryTime(LocalDate date, double weight, double limit) {
        double calc = weight / limit;
        double days = Math.ceil(calc);
        return date.plusDays(Math.round(days));
    }

    
    // Overrided
    @Override
    public void showInfo() {
        System.out.println("Full name: " + getFullName() + " |~| Phone number: " + getPhoneNumber() + " |~| Company name: " + getCompanyName() + 
        " |~| Driver status : " + getDriverStatusText() + " |~| Id: " + getId());
    }

    @Override
    public String getCompanyName() { return companyName; }
    public String getFullName() { return fullName; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
