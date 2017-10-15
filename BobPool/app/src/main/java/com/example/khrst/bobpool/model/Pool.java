package com.example.khrst.bobpool.model;

//import java.util.Calendar;
//import java.util.Date;
/**
 * Created by hpark on 10/14/17.
 */

public class Pool {
    private String restaurant, name, date, time, notes, capacity, currentNum;
    //private Calendar newCalendar;
    public Pool(String restaurant, String name, String date, String time, String notes, String capacity, String currentNum) {
        this.restaurant = restaurant;
        this.name = name;
        this.date = date;
        this.time = time;
        this.notes = notes;
        this.capacity = capacity;
        this.currentNum = currentNum;
    }
    public String getRestaurant() {
        return restaurant;
    }
    public String getName() {
        return name;
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

