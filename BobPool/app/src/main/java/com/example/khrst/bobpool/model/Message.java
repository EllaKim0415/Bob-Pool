package com.example.khrst.bobpool.model;

/**
 * Created by com93 on 10/15/2017.
 */
import android.graphics.Color;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Message {
    private String text;
    private String name;
    private String time;
    private String photoUrl;
    private int color;

    public Message() {

    }

    public Message(int color, String text, String name) {
        this(text, name, null);
        this.color = color;
    }

    public Message(String text, String name, String photoUrl) {
        this.text = text;
        this.name = name;
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd");
        DateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        this.time = dateFormat.format(date) + " at " + timeFormat.format(date);
        this.photoUrl = photoUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}