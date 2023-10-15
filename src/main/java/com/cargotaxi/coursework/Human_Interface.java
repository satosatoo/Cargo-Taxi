package com.cargotaxi.coursework;

interface Human_Interface {
    String companyName = "ACV";

    String getFullName();
    String getPhoneNumber();
    String getCompanyName();
    void setFullName(String fullName);
    void setPhoneNumber(String phoneNumber);
    String showInfo();
}