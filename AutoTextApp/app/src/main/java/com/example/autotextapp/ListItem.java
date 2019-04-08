package com.example.autotextapp;

import android.graphics.Bitmap;

import java.sql.Time;
import java.util.Date;

public class ListItem {
    public String contactName;
    public Bitmap messengerIcon;
    public String messageSendDate;
    public String sendTime;
    public String message;

public String toString(){
    return "Contact Name: " + this.contactName + "Message Send Date: " + this.messageSendDate +
            "Send Time: " + this.sendTime + "Message: " + this.message;
    }
}
