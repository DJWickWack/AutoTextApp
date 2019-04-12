package com.example.autotextapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;

import java.sql.Time;
import java.util.Date;

import static com.example.autotextapp.AddEvent.date;

public class ListItem extends AppCompatActivity {
    public String contactName;
    public Bitmap messengerIcon;
    public String messageSendDate;
    public String sendTime;
    public String message;
    public int id;

    public ListItem(){

    }

public String toString(){
    return "ID: " + id + "Contact Name: " + this.contactName + "Message Send Date: " + this.messageSendDate +
            "Send Time: " + this.sendTime + "Message: " + this.message;
    }


    public ListItem ReturnThisItem(){
        return this;
    }
}
