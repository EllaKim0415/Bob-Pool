package com.example.khrst.bobpool.model;

//import java.util.Calendar;
//import java.util.Date;
/**
 * Created by hpark on 10/14/17.
 */

public class Pool {
    private String name, date, time, notes;
    //private Calendar newCalendar;
    public Pool(String name, String date, String time, String notes) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.notes = notes;
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
    public void setDate(String date) {
        this.date = date;
    }
}
