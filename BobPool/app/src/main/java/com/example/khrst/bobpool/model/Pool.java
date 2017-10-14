package com.example.khrst.bobpool.model;

//import java.util.Calendar;
//import java.util.Date;
/**
 * Created by hpark on 10/14/17.
 */

public class Pool {
    private String name, date, maxnum, time, notes;
    //private Calendar newCalendar;
    public Pool(String name, String date, String maxnum, String time, String notes) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.maxnum = maxnum;
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
    public String getMaxnum() {
        return maxnum;
    }
}
