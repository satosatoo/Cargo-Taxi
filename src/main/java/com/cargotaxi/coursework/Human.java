package com.cargotaxi.coursework;

abstract class Human {
    String fullName;
    String phoneNumber;
    String companyName = "ACV";

    Human(String fullName, String phoneNumber) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public String showInfo() { return null; }
    public String getFullName() { return null; }
    public String getPhoneNumber() { return null; }
    public String getCompanyName() { return null; }
    public void setFullName(String fullName) {}
    public void setPhoneNumber(String phoneNumber) {}
}