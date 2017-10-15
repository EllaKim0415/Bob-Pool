package com.example.khrst.bobpool.model;

//import java.util.Calendar;
//import java.util.Date;
/**
 * Created by hpark on 10/14/17.
 */

public class Pool {
    private String name, phoneNumber, restaurant, title, date, time, notes, capacity, currentNum;
    //private Calendar newCalendar;
    public Pool(String name, String phoneNumber, String restaurant, String title, String date, String time, String notes, String capacity, String currentNum) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.restaurant = restaurant;
        this.title = title;
        this.date = date;
        this.time = time;
        this.notes = notes;
        this.capacity = capacity;
        this.currentNum = currentNum;
    }
    public String getName() {
        return name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getRestaurant() {
        return restaurant;
    }
    public String getTitle() {
        return title;
    }
    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }
    public String getNotes() {
        return notes;
    }
    public String getCapacity() {
        return capacity;
    }
    public String getCurrentNum() {
        return currentNum;
    }
}

