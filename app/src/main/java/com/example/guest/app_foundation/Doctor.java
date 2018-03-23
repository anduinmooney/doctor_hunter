package com.example.guest.app_foundation;


public class Doctor {
    private String firstName;
    private String lastName;
    private String gender;
    private String phone;
    private String newPatient;
    private String address;
    private String imageUrl;

    public Doctor(String firstName, String lastName, String gender, String phone, String newPatient, String address, String imageUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phone = phone;
        this.newPatient = newPatient;
        this.address = address;
        this.imageUrl = imageUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getNewPatient() {
        return newPatient;
    }

    public String getAddress() {
        return address;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
