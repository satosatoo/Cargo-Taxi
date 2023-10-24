package com.cargotaxi.coursework;

abstract class Human_Abstract {
    String fullName;
    String phoneNumber;
    String companyName = "ACV";

    Human_Abstract(String fullName, String phoneNumber) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    String showInfo() { return null; }
    String getFullName() { return null; }
    String getPhoneNumber() { return null; }
    String getCompanyName() { return null; }
    void setFullName(String fullName) {}
    void setPhoneNumber(String phoneNumber) {}
}