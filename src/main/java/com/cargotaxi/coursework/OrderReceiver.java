package com.cargotaxi.coursework;

import java.util.ArrayList;
import java.util.List;

public class OrderReceiver extends Human_Abstract implements Human_Interface {
    public static List<OrderReceiver> orderReceiverList = new ArrayList<>();
    static int id;
    final private int personalId;
    private String officeAddress;

    OrderReceiver(String fullName, String phoneNumber, String officeAddress) {
        super(fullName, phoneNumber);
        this.officeAddress = officeAddress;
        this.personalId = OrderReceiver.id++;
    }

    public void createOrderReceiver(String fullName, String phoneNumber, String officeAddress) {
        OrderReceiver orderReceiver = new OrderReceiver(fullName, phoneNumber, officeAddress);
        OrderReceiver.orderReceiverList.add(orderReceiver);
    }

    public void deleteOrderReceiverById(ArrayList<OrderReceiver> array, int id) {
        for (OrderReceiver obj : array) {
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