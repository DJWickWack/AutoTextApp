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

    private int id;

    public ListItem(){
        SetID();
    }

public String toString(){
    return "Contact Name: " + this.contactName + "Message Send Date: " + this.messageSendDate +
            "Send Time: " + this.sendTime + "Message: " + this.message;
    }

    private void SetID(){
        String path="/data/data/"+getPackageName()+"/sample.db";
        SQLiteDatabase db= SQLiteDatabase.openOrCreateDatabase(path,null);
        Cursor idCursor = db.rawQuery("SELECT _ID FROM  info  WHERE name =" + "'"+contactName+"'" + "AND message" +
              "'"+message+"'" + "AND date=" + "'"+messageSendDate+"'" +"AND time=" +  "'"+sendTime+"'", null);
        id = Integer.parseInt(idCursor.getString(0));
    }

    public int GetID(){
        return id;
    }
}
