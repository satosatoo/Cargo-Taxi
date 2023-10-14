package com.cargotaxi.coursework;

import java.util.ArrayList;
import java.util.List;

public class OrderTaker extends Human_Abstract implements Human_Interface {
    public static List<OrderTaker> orderTakerList = new ArrayList<>();
    static int id;
    final private int personalId;
    private String officeAddress;

    OrderTaker(String fullName, String phoneNumber, String officeAddress) {
        super(fullName, phoneNumber);
        this.officeAddress = officeAddress;
        this.personalId = OrderTaker.id++;
    }

    public void createOrderTaker(String fullName, String phoneNumber, String officeAddress) {
        OrderTaker orderTaker = new OrderTaker(fullName, phoneNumber, officeAddress);
        OrderTaker.orderTakerList.add(orderTaker);
    }

    public void deleteOrderTakerById(ArrayList<OrderTaker> array, int id) {
        for (OrderTaker obj : array) {
            if (obj.getId() == id) {
                obj = null;
            }
        }
    }

    // Overrided
    @Override
    public void showInfo() {
        System.out.println("Full name: " + getFullName() + " |~| Phone number: " + getPhoneNumber() + " |~| Company name: " + getCompanyName() + 
        " |~| Office address: " + getOfficeAddress() + " |~| Id: " + getId());
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

    public int getId() { return personalId; }
}