package org.example;

public class Location {
    String address;
    int pinCode;
    String city;
    String state;
    String country;

    public Location(int pinCode, String city, String state, String country) {
        this.pinCode = pinCode;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Location{" +
                "pinCode=" + pinCode +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
