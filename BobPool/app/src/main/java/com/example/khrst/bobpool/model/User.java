package com.example.khrst.bobpool.model;

/**
 * Created by khrst on 10/14/2017.
 */

public class User {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String username;
    private String password;

    public User(String username, String firstName, String lastName,
                String phoneNumber, String address, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
    }

    public String getName() {
        return firstName + lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }
}
