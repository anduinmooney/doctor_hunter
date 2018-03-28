package com.example.guest.app_foundation.models;

import org.parceler.Parcel;

@Parcel
public class Doctor {
    private String firstName;
    private String lastName;
    private String gender;
    private String phone;
    private String newPatient;
    private String address;
    private String imageUrl;
    private String city;
    private String state;
    private String zip;
    private String bio;
    private String specialty;

    public Doctor(){}



    public Doctor(String firstName, String lastName, String phone, String gender, String imageUrl, String newPatient, String address, String city, String state, String zip, String bio, String specialty) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phone = phone;
        this.newPatient = newPatient;
        this.address = address;
        this.imageUrl = imageUrl;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.bio = bio;
        this.specialty = specialty;
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

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }


    public String getBio() {
        return bio;
    }

    public String getSpecialty() {
        return specialty;
    }

}
